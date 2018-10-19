package com.lianhe.nine.intface.service;

import com.lianhe.nine.intface.po.Permission;
import com.lianhe.nine.intface.po.Role;
import com.lianhe.nine.intface.po.User;
import com.lianhe.nine.intface.service.IBaseService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author Rubi
 * @since 2018-10-17
 */
@Transactional(rollbackFor = Exception.class)
public interface IUserService extends IBaseService<User> {
//    /**
//     * 若返回 0 注册失败，>0 成功
//     *
//     * @param username
//     * @param password
//     * @param nick_name
//     * @return
//     */
//
//    boolean register(String username, String password, String nick_name) throws Exception;



    /**
     * @param username
     * @return
     * @throws Exception
     */
    Set<Role> getRoles(String username) throws Exception;

    /**
     * @param username
     * @return
     * @throws Exception
     */
    Set<Permission> getPermissions(String username) throws Exception;

    /**
     * @param username
     * @return
     * @throws Exception
     */
    User getByUsername(String username) throws Exception;




//    /**
//     * @param user_id
//     * @param password
//     * @param nickname
//     * @param description
//     * @return
//     * @throws Exception
//     */
//    int updateById(int user_id, String password, String nickname, String description) throws Exception;
//
//    /**
//     *
//     * @return
//     */
//    List<User> queryAll()throws Exception;
//
//    /**
//     *
//     * @return
//     */
//    List<User> queryAllByPage()throws Exception;
}
