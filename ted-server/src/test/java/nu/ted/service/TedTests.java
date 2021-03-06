package nu.ted.service;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import nu.ted.Server;
import nu.ted.domain.SeriesBackendWrapper;
import nu.ted.generated.Event;
import nu.ted.generated.EventType;
import nu.ted.generated.ImageFile;
import nu.ted.generated.ImageType;
import nu.ted.generated.InvalidOperation;
import nu.ted.generated.Series;
import nu.ted.generated.SeriesSearchResult;
import nu.ted.generated.TorrentSource;
import nu.ted.guide.TestGuide;

import org.apache.thrift.TException;
import org.junit.Test;

public class TedTests
{
	@Test
	public void testFindExactSeries() throws TException
	{
		TedServiceImpl ted = new TedServiceImpl(Server.createDefaultTed(), new TestGuide());
		List<SeriesSearchResult> results = ted.search("Exactly");

		assertNotNull("Ted not returning a List", results);
		assertEquals("Only one show expected", 1, results.size());

		SeriesSearchResult exact = results.get(0);
		assertEquals("Name incorrect", "Exactly", exact.getName());
	}

	@Test
	public void testFindMultipleSeries() throws TException {
		TedServiceImpl ted = new TedServiceImpl(Server.createDefaultTed(), new TestGuide());
		List<SeriesSearchResult>  results = ted.search("General");

		assertNotNull("Ted not returning a List", results);
		assertEquals("Expected multiple results", 3, results.size());

		SeriesSearchResult series = results.get(0);
		assertEquals("Name incorrect", "General1", series.getName());
		series = results.get(1);
		assertEquals("Name incorrect", "General2", series.getName());
		series = results.get(2);
		assertEquals("Name incorrect", "General3", series.getName());

	}

	@Test
	public void shouldStartOutEmpty() throws TException {
		TedServiceImpl ted = new TedServiceImpl(Server.createDefaultTed(), new TestGuide());

		List<Series> watched = ted.getWatching();
		assertNotNull(watched);
		assertEquals(0, watched.size());
	}

	@Test
	public void shouldBeAbleToWatchOneValidShow() throws TException, InvalidOperation {
		TedServiceImpl ted = new TedServiceImpl(Server.createDefaultTed(), new TestGuide());

		ted.startWatching("E");

		List<Series> watched = ted.getWatching();
		assertEquals(1, watched.size());

		Series series = watched.get(0);
		assertEquals("Exactly", series.getName());

		assertNull(new SeriesBackendWrapper(series).getLastEpisode());

	}


	@Test
	public void shouldBeAbleToStopWatchingAShowYourWatching() throws TException, InvalidOperation {
		TedServiceImpl ted = new TedServiceImpl(Server.createDefaultTed(), new TestGuide());
		short id = ted.startWatching("E");

		ted.stopWatching(id);

		List<Series> watched = ted.getWatching();
		assertEquals(0, watched.size());
	}

	@Test
	public void shouldLoadBannerForValidSearchId() throws TException {
		TedServiceImpl ted = new TedServiceImpl(Server.createDefaultTed(), new TestGuide());

		ImageFile image = ted.getImageByGuideId("E", ImageType.BANNER);
		assertNotNull(image);
		assertEquals("image/banner", image.getMimetype());
		assertArrayEquals("BANNER".getBytes(), image.getData().array());
	}

	@Test
	public void shouldLoadBannerThumbnailForValidSearchId() throws TException {
		TedServiceImpl ted = new TedServiceImpl(Server.createDefaultTed(), new TestGuide());

		ImageFile image = ted.getImageByGuideId("E", ImageType.BANNER_THUMBNAIL);
		assertNotNull(image);
		assertEquals("image/thumbnail", image.getMimetype());
		assertArrayEquals("THUMBNAIL".getBytes(), image.getData().array());
	}

	@Test
	public void shouldLoadBannerForValidSeriesId() throws TException, InvalidOperation {
		TedServiceImpl ted = new TedServiceImpl(Server.createDefaultTed(), new TestGuide());
		short id = ted.startWatching("E");

		ImageFile image = ted.getImageBySeriesId(id, ImageType.BANNER);
		assertNotNull(image);
		assertEquals("image/banner", image.getMimetype());
		assertArrayEquals("BANNER".getBytes(), image.getData().array());
	}

	@Test
	public void shouldLoadBannerThumbnailForValidSeriesId() throws TException, InvalidOperation {
		TedServiceImpl ted = new TedServiceImpl(Server.createDefaultTed(), new TestGuide());
		short id = ted.startWatching("E");

		ImageFile image = ted.getImageBySeriesId(id, ImageType.BANNER_THUMBNAIL);
		assertNotNull(image);
		assertEquals("image/thumbnail", image.getMimetype());
		assertArrayEquals("THUMBNAIL".getBytes(), image.getData().array());
	}

	@Test
	public void ensureGetOverviewFromService() throws TException
	{
		TedServiceImpl ted = new TedServiceImpl(Server.createDefaultTed(), new TestGuide());
		assertEquals("An Overview", ted.getOverview("E"));
	}

	@Test
	public void shouldRegisterWatchedListChangedEventIfStartWatching() throws TException, InvalidOperation{
		TedServiceImpl ted = new TedServiceImpl(Server.createDefaultTed(), new TestGuide());
		String eventClientId = ted.registerClientWithEventRegistry();

		ted.startWatching("E");
		List<Event> events = ted.getEvents();
		assertEquals(1, events.size());
		assertEquals(EventType.WATCHED_SERIES_ADDED, events.get(0).getType());
	}

	@Test
	public void shouldOnlyAllowShowsToBeAddedOnce() throws TException, InvalidOperation{
		TedServiceImpl ted = new TedServiceImpl(Server.createDefaultTed(), new TestGuide());

		ted.startWatching("E");
		try {
			ted.startWatching("E");
		} catch (InvalidOperation e) {
			return; /* pass */
		}
		fail("Shouldn't be able to add show twice.");
	}

	@Test
	public void shouldRegisterWatchedListChangedEventIfStopWatching() throws TException, InvalidOperation {
		TedServiceImpl ted = new TedServiceImpl(Server.createDefaultTed(), new TestGuide());
		String eventClientId = ted.registerClientWithEventRegistry();
		short id = ted.startWatching("E");
		// Flush the registry for this client.
		ted.getEvents();

		ted.stopWatching(id);
		List<Event> events = ted.getEvents();
		assertEquals(1, events.size());
		assertEquals(EventType.WATCHED_SERIES_REMOVED, events.get(0).getType());
	}

	@Test
	public void ensureGetSeriesReturnsWatchedSeries() throws Exception {
		TedServiceImpl ted = new TedServiceImpl(Server.createDefaultTed(), new TestGuide());
		short seriesId = ted.startWatching("E");
		Series series = ted.getSeries(seriesId);
		assertNotNull("Series was not found.", series);
		assertEquals(seriesId, series.getUid());
	}

	@Test
	public void ensureNullWhenGetSeriesDoesNotFindSeriesWithSpecifiedId() throws Exception {
		TedServiceImpl ted = new TedServiceImpl(Server.createDefaultTed(), new TestGuide());
		Series series = ted.getSeries(new Short("123"));
		assertNull("Series should not have been found.", series);
	}

	// This test might not make sense if we have a default list created.
	@Test
	public void ensureNoTorrentSourcesExistByDefault() throws Exception {
		TedServiceImpl ted = new TedServiceImpl(Server.createDefaultTed(), new TestGuide());

		Map<String, List<TorrentSource>> allTorrentSources = ted.getAllTorrentSources();
		assertNotNull(allTorrentSources);
		assertEquals("Should have no sources by default", 0, allTorrentSources.size());
	}

	@Test
	public void ensureGettingNonExistentTorrentSourceReturnsEmptyListButDoesntCreateIt() throws Exception {
		TedServiceImpl ted = new TedServiceImpl(Server.createDefaultTed(), new TestGuide());

		List<TorrentSource> sources = ted.getTorrentSources("Test");
		assertNotNull(sources);
		assertEquals(0, sources.size());

		Map<String, List<TorrentSource>> allTorrentSources = ted.getAllTorrentSources();
		assertNotNull(allTorrentSources);
		assertEquals("Should not have created the non-existent", 0, allTorrentSources.size());
	}

	@Test
	public void ensureAddingTorrentSourceWorks() throws Exception {
		TedServiceImpl ted = new TedServiceImpl(Server.createDefaultTed(), new TestGuide());

		TorrentSource source = new TorrentSource("type", "name", "location");
		List<TorrentSource> torrentSourceList = new LinkedList<TorrentSource>();
		torrentSourceList.add(source);

		ted.editTorrentSources("Test", torrentSourceList);

		List<TorrentSource> sources = ted.getTorrentSources("Test");
		assertNotNull(sources);
		assertEquals("Should have the one I entered", 1, sources.size());
		assertEquals("And it's values should be correct", "location", sources.get(0).getLocation());

		Map<String, List<TorrentSource>> allTorrentSources = ted.getAllTorrentSources();
		assertNotNull(allTorrentSources);
		assertEquals("Should have one by now", 1, allTorrentSources.size());
		assertTrue(allTorrentSources.containsKey("Test"));
	}

	@Test
	public void ensureRemovingTorrentSourceWorks() throws Exception {
		TedServiceImpl ted = new TedServiceImpl(Server.createDefaultTed(), new TestGuide());

		TorrentSource source = new TorrentSource("type", "name", "location");
		List<TorrentSource> torrentSourceList = new LinkedList<TorrentSource>();
		torrentSourceList.add(source);

		ted.editTorrentSources("Test", torrentSourceList);
		ted.removeTorrentSources("Test");

		Map<String, List<TorrentSource>> allTorrentSources = ted.getAllTorrentSources();
		assertNotNull(allTorrentSources);
		assertEquals("Should have removed it, so none left", 0, allTorrentSources.size());
	}
}
