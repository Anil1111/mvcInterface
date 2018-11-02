package com.nine.intface.common.shiro;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.session.mgt.eis.MemorySessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * @author : Rubi
 * @version : 2018-10-12 16:13
 */
public class RubiSessionDao extends MemorySessionDAO {

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
//        log.info("doCreate");
//
//        return super.create(session);
//    }
//
//    @Override
//    protected Session doReadSession(Serializable sessionId) {
//        log.info("doReadSession");
//        return super.readSession(sessionId);
//    }
//
////    @Override
////    public Collection<Session> getActiveSessions() {
////        log.info("getActiveSessions");
////        return super.getActiveSessions();
////    }
//
//    @Override
//    protected void doUpdate(Session session) {
//        log.info("doUpdate");
//        super.update(session);
//    }
//
//    @Override
//    protected void doDelete(Session session) {
//        log.info("doDelete");
//        super.delete(session);
//    }
}
