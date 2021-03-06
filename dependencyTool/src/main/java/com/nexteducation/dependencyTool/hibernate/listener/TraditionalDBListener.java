package com.nexteducation.dependencyTool.hibernate.listener;

import java.io.IOException;

import org.hibernate.event.spi.PostDeleteEvent;
import org.hibernate.event.spi.PostDeleteEventListener;
import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.event.spi.PostInsertEventListener;
import org.hibernate.event.spi.PostUpdateEvent;
import org.hibernate.event.spi.PostUpdateEventListener;
import org.hibernate.persister.entity.EntityPersister;
import org.springframework.stereotype.Component;

/**
 * @author ashishpratap
 *
 */
@Component
public class TraditionalDBListener
		implements PostInsertEventListener, PostDeleteEventListener, PostUpdateEventListener {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void onPostInsert(final PostInsertEvent event) {
		try {
			processEntity(event.getEntity(), 1);
		} catch (final IOException e) {

			e.printStackTrace();
		}
	}

	public boolean requiresPostCommitHanding(final EntityPersister persister) {
		return false;
	}

	@Override
	public void onPostDelete(final PostDeleteEvent event) {
		try {
			processEntity(event.getEntity(), 2);
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onPostUpdate(final PostUpdateEvent event) {
		try {
			processEntity(event.getEntity(), 3);
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	private void processEntity(final Object entity, final int operation) throws IOException {
	}

}
