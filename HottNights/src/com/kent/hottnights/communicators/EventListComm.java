package com.kent.hottnights.communicators;

public interface EventListComm {
	public void allEventInfo(int pEventId, String pEventName, String pEventPic,
			String pEventDescr, String pEventAbout, float pEventLat,
			float pEventLong, int pFeatureId, int pDrinksId, int pDressId,
			int pPhotoId);

}
