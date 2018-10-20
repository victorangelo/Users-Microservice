package com.viktor.listener;

import javax.management.AttributeChangeNotification;
import javax.management.Notification;
import javax.management.NotificationListener;

/**
 * @author Viktor Angelutsa
 *
 */
public class ResourceListener implements NotificationListener {

	@Override
	public void handleNotification(Notification notification, Object handback) {
		// Process the different types of notifications fired by the
		// simple standard MBean.
		String type = notification.getType();

		System.out.println("\n\t>> SimpleStandardListener handles received notification:"
				+ "\n\t>> -----------------------------------------------------");
		try {
			if (type.equals(AttributeChangeNotification.ATTRIBUTE_CHANGE)) {

				System.out.println("\t>> Notification is: \"" + notification.getMessage());
				System.out.println("\t>> Value = " + notification.getUserData());

			} else {
				System.out.println("\t>> Unknown event type (?)\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

}
