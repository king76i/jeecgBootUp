package org.jeecg.modules.vo;

import java.util.List;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelEntity;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: 测试用户
 * @Author: jeecg-boot
 * @Date:   2022-03-30
 * @Version: V1.0
 */
@Data
@ApiModel(value="user_infoPage对象", description="测试用户")
public class UserInfoPage {

	/**主键*/
	@ApiModelProperty(value = "主键")
    private String id;
	/**创建人*/
	@ApiModelProperty(value = "创建人")
    private String createBy;
	/**创建日期*/
	@ApiModelProperty(value = "创建日期")
    private Date createTime;
	/**更新人*/
	@ApiModelProperty(value = "更新人")
    private String updateBy;
	/**更新日期*/
	@ApiModelProperty(value = "更新日期")
    private Date updateTime;
	/**所属部门*/
	@ApiModelProperty(value = "所属部门")
    private String sysOrgCode;
	/**用户姓名*/
	@Excel(name = "用户姓名", width = 15)
	@ApiModelProperty(value = "用户姓名")
    private String userName;
	/**openId*/
	@Excel(name = "openId", width = 15)
	@ApiModelProperty(value = "openId")
    private String openId;
	/**unionId*/
	@Excel(name = "unionId", width = 15)
	@ApiModelProperty(value = "unionId")
    private String unionId;
	/**用户头像*/
	@Excel(name = "用户头像", width = 15)
	@ApiModelProperty(value = "用户头像")
    private String userImg;
	/**用户手机号*/
	@Excel(name = "用户手机号", width = 15)
	@ApiModelProperty(value = "用户手机号")
    private String userPhone;


}
