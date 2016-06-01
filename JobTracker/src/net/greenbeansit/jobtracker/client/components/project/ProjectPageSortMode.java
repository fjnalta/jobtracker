package net.greenbeansit.jobtracker.client.components.project;

/**
 * Contains the sort modes for the {@link ProjectPage}.
 * 
 * @author Max Blatt
 */
enum ProjectPageSortMode
{
	/**
	 * Sort by name in upward direction.
	 */
	NAME_UP,
	
	/**
	 * Sort by name in downward direction.
	 */
	NAME_DOWN,
	
	/**
	 * Sort by used budget percents in upward direction.
	 */
	USED_BUDGET_PERCENT_UP,
	
	/**
	 * Sort by used budget percents in downward direction.
	 */
	USED_BUDGET_PERCENT_DOWN,
	
	/**
	 * Sort by locked status in upward direction.
	 */
	LOCKED_UP,
	
	/**
	 * Sort by locked status in downward direction.
	 */
	LOCKED_DOWN
}

