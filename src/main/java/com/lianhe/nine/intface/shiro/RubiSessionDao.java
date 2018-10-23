package com.lianhe.nine.intface.shiro;

import com.google.common.collect.Maps;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.MemorySessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

/**
 * @author : Rubi
 * @version : 2018-10-12 16:13
 */
public class RubiSessionDao extends MemorySessionDAO {
    private static final Logger logger = LoggerFactory.getLogger(RubiSessionDao.class);

    @Override
    protected Serializable doCreate(Session session) {
        SimpleSession simpleSession =new SimpleSession();
         simpleSession.setId(session.getId());
         simpleSession.setHost(session.getHost());
         simpleSession.setStartTimestamp(session.getStartTimestamp());
         simpleSession.setLastAccessTime(session.getLastAccessTime());
         simpleSession.setTimeout(session.getTimeout());
         storeSession(simpleSession.getId(),simpleSession);
         return simpleSession.getId();
    }



    //
//
//    @Override
//    protected Serializable doCreate(Session session) {
//        logger.info("doCreate");
//
//        return super.create(session);
//    }
//
//    @Override
//    protected Session doReadSession(Serializable sessionId) {
//        logger.info("doReadSession");
//        return super.readSession(sessionId);
//    }
//
////    @Override
////    public Collection<Session> getActiveSessions() {
////        logger.info("getActiveSessions");
////        return super.getActiveSessions();
////    }
//
//    @Override
//    protected void doUpdate(Session session) {
//        logger.info("doUpdate");
//        super.update(session);
//    }
//
//    @Override
//    protected void doDelete(Session session) {
//        logger.info("doDelete");
//        super.delete(session);
//    }
}