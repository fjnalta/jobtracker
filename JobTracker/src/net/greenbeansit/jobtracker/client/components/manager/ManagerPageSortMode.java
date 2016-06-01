package net.greenbeansit.jobtracker.client.components.manager;

/**
 * Defines sort modes for the {@link ManagerPage}.
 * 
 * @author Max Blatt
 */
enum ManagerPageSortMode
{
	/**
	 * Sorts by the employee's name in upward direction.
	 */
	ALPHABETICAL_UP,

	/**
	 * Sorts by the employee's name in downward direction.
	 */
	ALPHABETICAL_DOWN,

	/**
	 * Sorts by the utilization of the employee in upward direction.
	 */
	UTILIZATION_UP,

	/**
	 * Sorts by the utilization of the employee in downward direction.
	 */
	UTILIZATION_DOWN
}
