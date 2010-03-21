package nu.ted.gwt.client.page;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

import net.bugsquat.gwtsite.client.PageLoader;
import net.bugsquat.gwtsite.client.page.Page;
import net.bugsquat.gwtsite.client.page.PageController;

public class SearchPageController extends PageController<SearchPage>
{
	private final SearchServiceAsync searchService = (SearchServiceAsync)GWT.create(SearchService.class);

	public SearchPageController() {
		this.page = new SearchPage(this);
	}

	public void loadData(Page<SearchPageController> page) {
		loadListener.pageDataHasBeenLoaded(page);
	}

	public void search(String filter) {
		PageLoader.getInstance().showLoadingPage();
		searchService.search(filter, new AsyncCallback<List<String>>() {
			
			@Override
			public void onSuccess(List<String> results) {
				hideLoadingIndicator();
				page.setSearchResults(results);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				System.out.println(caught);
				hideLoadingIndicator();
				Window.alert("An error occurred: " + caught.getMessage());
			}
			
			private void hideLoadingIndicator() {
				PageLoader.getInstance().hideLoadingPage();
			}
		});
	}

	@Override
	public SearchPage getPage() {
		return page;
	}

}
