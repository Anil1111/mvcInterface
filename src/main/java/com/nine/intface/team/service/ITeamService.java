package com.nine.intface.team.service;


import com.nine.intface.common.service.IBaseService;
import com.nine.intface.team.po.Team;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * team 表 服务类
 * </p>
 *
 * @author Rubi
 * @since 2018-10-23
 */

@Transactional(rollbackFor = Exception.class)
public interface ITeamService extends IBaseService<Team> {

}
