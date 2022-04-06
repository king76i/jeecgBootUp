package org.jeecg.modules.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.constant.CommonConstant;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.system.util.JwtUtil;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.common.util.RedisUtil;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.entity.UserInfo;
import org.jeecg.modules.service.IUserInfoService;
import org.jeecg.modules.vo.UserInfoPage;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description: 测试用户
 * @Author: jeecg-boot
 * @Date:   2022-03-30
 * @Version: V1.0
 */
@Api(tags="测试用户")
@RestController
@RequestMapping("/bsitest/userInfo")
@Slf4j
public class UserInfoController {
	@Autowired
	private IUserInfoService userInfoService;

	 @Autowired
	 private RedisUtil redisUtil;


	 /**
	  * 生成 api token
	  *
	  * @param id
	  * @param req
	  * @return
	  */
	 //@AutoLog(value = "测试用户-生成token")
	 @ApiOperation(value="测试用户-生成token", notes="测试用户-生成token")
	 @GetMapping(value = "/getToken")
	 public Result<UserInfo> getToken(String id,HttpServletRequest req) {
		 UserInfo info = userInfoService.selectOne(id);
		 // 生成api2 token
		 String token = JwtUtil.signVer2(info.getId(), "123456");
		 // 设置token缓存有效时间
		 redisUtil.set(CommonConstant.PREFIX_USER_TOKEN + token, token);
		 redisUtil.expire(CommonConstant.PREFIX_USER_TOKEN + token, JwtUtil.EXPIRE_TIME * 84 ); //一周有效期的token
		 return Result.OK(token);
	 }
	 /**
	  * 根据id查询
	  *
	  * @param id
	  * @param req
	  * @return
	  */
	 //@AutoLog(value = "测试用户-根据id获取用户")
	 @ApiOperation(value="测试用户-根据id获取用户", notes="测试用户-根据id获取用户")
	 @GetMapping(value = "/getOnes")
	 public UserInfo getOnes(String id,HttpServletRequest req) {
		 UserInfo info = userInfoService.selectOne(id);
		 return info;
	 }
	/**
	 * 分页列表查询
	 *
	 * @param userInfo
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "测试用户-分页列表查询")
	@ApiOperation(value="测试用户-分页列表查询", notes="测试用户-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<UserInfo>> queryPageList(UserInfo userInfo,
												 @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
												 @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
												 HttpServletRequest req) {
		QueryWrapper<UserInfo> queryWrapper = QueryGenerator.initQueryWrapper(userInfo, req.getParameterMap());
		Page<UserInfo> page = new Page<UserInfo>(pageNo, pageSize);
		IPage<UserInfo> pageList = userInfoService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	/**
	 *   添加
	 *
	 * @param userInfoPage
	 * @return
	 */
	@AutoLog(value = "测试用户-添加")
	@ApiOperation(value="测试用户-添加", notes="测试用户-添加")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody UserInfoPage userInfoPage) {
		UserInfo userInfo = new UserInfo();
		BeanUtils.copyProperties(userInfoPage, userInfo);
		userInfoService.saveMain(userInfo);
		return Result.OK("添加成功！");
	}

	/**
	 *  编辑
	 *
	 * @param userInfoPage
	 * @return
	 */
	@AutoLog(value = "测试用户-编辑")
	@ApiOperation(value="测试用户-编辑", notes="测试用户-编辑")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody UserInfoPage userInfoPage) {
		UserInfo userInfo = new UserInfo();
		BeanUtils.copyProperties(userInfoPage, userInfo);
		UserInfo userInfoEntity = userInfoService.getById(userInfo.getId());
		if(userInfoEntity==null) {
			return Result.error("未找到对应数据");
		}
		userInfoService.updateMain(userInfo);
		return Result.OK("编辑成功!");
	}

	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "测试用户-通过id删除")
	@ApiOperation(value="测试用户-通过id删除", notes="测试用户-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		userInfoService.delMain(id);
		return Result.OK("删除成功!");
	}

	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "测试用户-批量删除")
	@ApiOperation(value="测试用户-批量删除", notes="测试用户-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.userInfoService.delBatchMain(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "测试用户-通过id查询")
	@ApiOperation(value="测试用户-通过id查询", notes="测试用户-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<UserInfo> queryById(@RequestParam(name="id",required=true) String id) {
		UserInfo userInfo = userInfoService.getById(id);
		if(userInfo==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(userInfo);

	}


    /**
    * 导出excel
    *
    * @param request
    * @param userInfo
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, UserInfo userInfo) {
      // Step.1 组装查询条件查询数据
      QueryWrapper<UserInfo> queryWrapper = QueryGenerator.initQueryWrapper(userInfo, request.getParameterMap());
      LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

      //Step.2 获取导出数据
      List<UserInfo> queryList = userInfoService.list(queryWrapper);
      // 过滤选中数据
      String selections = request.getParameter("selections");
      List<UserInfo> userInfoList = new ArrayList<UserInfo>();
      if(oConvertUtils.isEmpty(selections)) {
          userInfoList = queryList;
      }else {
          List<String> selectionList = Arrays.asList(selections.split(","));
          userInfoList = queryList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
      }

      // Step.3 组装pageList
      List<UserInfoPage> pageList = new ArrayList<UserInfoPage>();
      for (UserInfo main : userInfoList) {
          UserInfoPage vo = new UserInfoPage();
          BeanUtils.copyProperties(main, vo);
          pageList.add(vo);
      }

      // Step.4 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      mv.addObject(NormalExcelConstants.FILE_NAME, "测试用户列表");
      mv.addObject(NormalExcelConstants.CLASS, UserInfoPage.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("测试用户数据", "导出人:"+sysUser.getRealname(), "测试用户"));
      mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
      return mv;
    }

    /**
    * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
      MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
      Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
      for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
          MultipartFile file = entity.getValue();// 获取上传文件对象
          ImportParams params = new ImportParams();
          params.setTitleRows(2);
          params.setHeadRows(1);
          params.setNeedSave(true);
          try {
              List<UserInfoPage> list = ExcelImportUtil.importExcel(file.getInputStream(), UserInfoPage.class, params);
              for (UserInfoPage page : list) {
                  UserInfo po = new UserInfo();
                  BeanUtils.copyProperties(page, po);
                  userInfoService.saveMain(po);
              }
              return Result.OK("文件导入成功！数据行数:" + list.size());
          } catch (Exception e) {
              log.error(e.getMessage(),e);
              return Result.error("文件导入失败:"+e.getMessage());
          } finally {
              try {
                  file.getInputStream().close();
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
      }
      return Result.OK("文件导入失败！");
    }

}
