package dto;

import com.baomidou.mybatisplus.annotation.TableId;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
public class CaseTableDTO {
    /**
     * 主键
     */
    private Integer id;
    /**
     * 实习社区
     */
    private String internshipCommunity;
    /**
     * 实习开始时间
     */
    private Date internshipBegintime;
    /**
     * 实习结束时间
     */
    private Date internshipEndtime;
    /**
     * 工作地点
     */
    private String workLocation;
    /**
     * 参与人员
     */
    private Integer participants;
    /**
     * 案例名称
     */
    private String caseName;
    /**
     * 关键字
     */
    private String keywords;
    /**
     * 服务对象
     */
    private String serviceTarget;
    /**
     * 案例类型
     */
    private String caseType;
    /**
     * 上传资源日期
     */
    private Date uploadresourceDate;
    /**
     * 上传的文件
     */
    private MultipartFile[] files;
    /**
     * 1代表典型个案，2代表小组活动案例
     */
    private Integer type;
    /**
     * 1代表通过审核，0代表待审核
     */
    private String status;
    /**
    * 创建人 user_id
    */
    private Integer createBy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInternshipCommunity() {
        return internshipCommunity;
    }

    public void setInternshipCommunity(String internshipCommunity) {
        this.internshipCommunity = internshipCommunity;
    }

    public Date getInternshipBegintime() {
        return internshipBegintime;
    }

    public void setInternshipBegintime(Date internshipBegintime) {
        this.internshipBegintime = internshipBegintime;
    }

    public Date getInternshipEndtime() {
        return internshipEndtime;
    }

    public void setInternshipEndtime(Date internshipEndtime) {
        this.internshipEndtime = internshipEndtime;
    }

    public String getWorkLocation() {
        return workLocation;
    }

    public void setWorkLocation(String workLocation) {
        this.workLocation = workLocation;
    }

    public Integer getParticipants() {
        return participants;
    }

    public void setParticipants(Integer participants) {
        this.participants = participants;
    }

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getServiceTarget() {
        return serviceTarget;
    }

    public void setServiceTarget(String serviceTarget) {
        this.serviceTarget = serviceTarget;
    }

    public String getCaseType() {
        return caseType;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }

    public Date getUploadresourceDate() {
        return uploadresourceDate;
    }

    public void setUploadresourceDate(Date uploadresourceDate) {
        this.uploadresourceDate = uploadresourceDate;
    }

    public MultipartFile[] getFiles() {
        return files;
    }

    public void setFiles(MultipartFile[] files) {
        this.files = files;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }
}
