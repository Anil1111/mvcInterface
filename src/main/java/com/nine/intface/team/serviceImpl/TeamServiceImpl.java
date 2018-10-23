package com.nine.intface.team.serviceImpl;


import com.nine.intface.common.serviceImpl.BaseServiceImpl;
import com.nine.intface.team.dao.TeamMapper;
import com.nine.intface.team.po.Team;
import com.nine.intface.team.service.ITeamService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * team 表 服务实现类
 * </p>
 *
 * @author Rubi
 * @since 2018-10-23
 */
@Service
public class TeamServiceImpl extends BaseServiceImpl<TeamMapper, Team> implements ITeamService {

}
