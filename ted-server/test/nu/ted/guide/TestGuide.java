package nu.ted.guide;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import nu.ted.generated.Episode;
import nu.ted.generated.EpisodeStatus;
import nu.ted.generated.ImageFile;
import nu.ted.generated.ImageType;
import nu.ted.generated.Series;
import nu.ted.generated.SeriesSearchResult;
import nu.ted.guide.GuideDB;

public class TestGuide implements GuideDB
{
	public static final short LAST_EPISODE_SEASON = 4;
	public static final short LAST_EPISODE_NUMBER = 2;

	@Override
	public List<SeriesSearchResult> search(String name) {
		List<SeriesSearchResult> results = new LinkedList<SeriesSearchResult>();
		if (name.equalsIgnoreCase("Exactly")) {
			results.add(new SeriesSearchResult("E", "Exactly"));
		} else if (name.equalsIgnoreCase("General")) {
			results.add(new SeriesSearchResult("1", "General1"));
			results.add(new SeriesSearchResult("2", "General2"));
			results.add(new SeriesSearchResult("3", "General3"));
		}
		return results;
	}

	@Override
	public ImageFile getImage(String id, ImageType type) {
		if (id.equals("E")) {
			return getImageFile(type);
		}
		return new ImageFile();
	}

	public ImageFile getImage(short uID, ImageType type) {
		if (uID == 2) {
			return getImageFile(type);
		}
		return new ImageFile();
	}

	@Override
	public String getName() {
		return "TEST";
	}

	@Override
	public String getName(String guideId) {
		if (guideId.equals("E")) {
			return "Exactly";
		}
		return null;
	}

	@Override
	public Episode getLastEpisode(String guideId, Calendar date) {
		Calendar twoDaysAgo = (Calendar) date.clone();
		twoDaysAgo.add(Calendar.DAY_OF_MONTH, -2);
		if (guideId.equals("E")) {
			return new Episode(LAST_EPISODE_SEASON, LAST_EPISODE_NUMBER, twoDaysAgo.getTimeInMillis(),
					EpisodeStatus.UNKNOWN);
		}
		return null;
	}

	@Override
	public String getOverview(String guideId) {
		return "An Overview";
	}

	@Override
	public List<Episode> getNewAiredEpisodes(String guideId, Calendar date,
			Episode lastEpisode) {
		Calendar oneDayAgo = (Calendar) date.clone();
		oneDayAgo.add(Calendar.DAY_OF_MONTH, -1);
		List<Episode> episodes = new LinkedList<Episode>();
		episodes.add(new Episode(LAST_EPISODE_SEASON, (short) (LAST_EPISODE_NUMBER + 1),
				oneDayAgo.getTimeInMillis(), EpisodeStatus.UNKNOWN));
		return episodes;
	}

	@Override
	public Series getSeries(String guideId, short id, Calendar date) {
		Episode episode = getLastEpisode(guideId, date);
		List<Episode> eplist = new LinkedList<Episode>();
		eplist.add(episode);
		if (guideId.equals("E")) {
			return new Series(id, "Exactly", getName(), guideId, eplist);
		}
		return null;
	}

	private ImageFile getImageFile(ImageType type) {
		if (type == ImageType.BANNER) {
			return new ImageFile("image/banner", "BANNER".getBytes());
		}
		else if (type == ImageType.BANNER_THUMBNAIL) {
			return new ImageFile("image/thumbnail", "THUMBNAIL".getBytes());
		}
		else {
			return new ImageFile();
		}
	}
}