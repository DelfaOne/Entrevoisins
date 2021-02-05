
package com.openclassrooms.entrevoisins.neighbour_list;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.contrib.ViewPagerActions;
import androidx.test.rule.ActivityTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.service.DummyNeighbourApiService;
import com.openclassrooms.entrevoisins.ui.activities.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.ClickChildViewWithId;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.core.IsNull.notNullValue;

/**
 * Test class for list of neighbours
 */
@RunWith(AndroidJUnit4.class)
public class NeighboursListTest {

    // This is fixed
    private static int ITEMS_COUNT = 12;

    private ListNeighbourActivity mActivity;
    private DummyNeighbourApiService favorite;

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule =
            new ActivityTestRule(ListNeighbourActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
    }

    /**
     * We ensure that our recyclerview is displaying at least on item
     */
    @Test
    public void myNeighboursList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(Matchers.allOf(withId(R.id.list_neighbours), isDisplayed()))
                .check(matches(hasMinimumChildCount(1)));
    }

    /**
     * Verify if activity_detail_neighbour is launched on item click
     */
    @Test
    public void onClick_verify_DetailActivity_isLaunched() {
        // When
        onView(Matchers.allOf(isDisplayed(), withId(R.id.list_neighbours)))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, new ClickChildViewWithId(R.id.item_root)));
        // Then
        onView(withId(R.id.activity_vue))
                .check(matches(isDisplayed()));
    }

    /**
     * Verify on click at the first position of recyclerview launched detail_activity with correct name in the textview
     */
    @Test
    public void onClick_verify_TextView_is_correctly_filled_with_correct_text() {
        // When
        onView(Matchers.allOf(isDisplayed() ,withId(R.id.list_neighbours)))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, new ClickChildViewWithId(R.id.item_root)));
        // Then
        onView(withId(R.id.username_txt))
                .check(matches(withText("Caroline")));
    }

    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void myNeighboursList_deleteAction_shouldRemoveItem() {
        // Given : We remove the element at position 2
        onView(Matchers.allOf(withId(R.id.list_neighbours), isDisplayed()))
                .check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(Matchers.allOf(withId(R.id.list_neighbours), isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 11
        onView(Matchers.allOf(withId(R.id.list_neighbours), isDisplayed()))
                .check(withItemCount(ITEMS_COUNT-1));
    }

    /**
     * When we add a neighbour on favorite list he should display on favorite fragment with the correct item
     */
    @Test
    public void check_if_favorites_fragment_only_display_favorites_neighbours() {
        //Given
        onView(Matchers.allOf(isDisplayed(), withId(R.id.list_neighbours)))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, new ClickChildViewWithId(R.id.item_root)));
        onView(withId(R.id.fav_float_btn))
                .perform(click());
        onView(withId(R.id.back_btn))
                .perform(click());
        //When
        onView(Matchers.allOf(withId(R.id.container)))
                .perform(ViewPagerActions.scrollRight());
        //Then
        onView(Matchers.allOf(withId(R.id.list_neighbours),  isDisplayed()))
                .check(withItemCount(1));
        onView(Matchers.allOf(withId(R.id.item_list_name),  isDisplayed()))
                .check(matches(withText("Caroline")));
    }
}