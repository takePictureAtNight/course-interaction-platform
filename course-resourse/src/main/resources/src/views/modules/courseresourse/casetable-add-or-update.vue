<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="实习社区" prop="internshipCommunity">
      <el-input v-model="dataForm.internshipCommunity" placeholder="实习社区"></el-input>
    </el-form-item>
    <el-form-item label="实习开始时间" prop="internshipBegintime">
      <el-input v-model="dataForm.internshipBegintime" placeholder="实习开始时间"></el-input>
    </el-form-item>
    <el-form-item label="实习结束时间" prop="internshipEndtime">
      <el-input v-model="dataForm.internshipEndtime" placeholder="实习结束时间"></el-input>
    </el-form-item>
    <el-form-item label="工作地点" prop="workLocation">
      <el-input v-model="dataForm.workLocation" placeholder="工作地点"></el-input>
    </el-form-item>
    <el-form-item label="参与人员" prop="participants">
      <el-input v-model="dataForm.participants" placeholder="参与人员"></el-input>
    </el-form-item>
    <el-form-item label="案例名称" prop="caseName">
      <el-input v-model="dataForm.caseName" placeholder="案例名称"></el-input>
    </el-form-item>
    <el-form-item label="关键字" prop="keywords">
      <el-input v-model="dataForm.keywords" placeholder="关键字"></el-input>
    </el-form-item>
    <el-form-item label="服务对象" prop="serviceTarget">
      <el-input v-model="dataForm.serviceTarget" placeholder="服务对象"></el-input>
    </el-form-item>
    <el-form-item label="案例类型" prop="caseType">
      <el-input v-model="dataForm.caseType" placeholder="案例类型"></el-input>
    </el-form-item>
    <el-form-item label="上传资源日期" prop="uploadresourceDate">
      <el-input v-model="dataForm.uploadresourceDate" placeholder="上传资源日期"></el-input>
    </el-form-item>
    <el-form-item label="上传资源文件url" prop="resourceUrl">
      <el-input v-model="dataForm.resourceUrl" placeholder="上传资源文件url"></el-input>
    </el-form-item>
    <el-form-item label="1代表典型个案，2代表小组活动案例" prop="type">
      <el-input v-model="dataForm.type" placeholder="1代表典型个案，2代表小组活动案例"></el-input>
    </el-form-item>
    <el-form-item label="1代表通过审核，0代表待审核" prop="status">
      <el-input v-model="dataForm.status" placeholder="1代表通过审核，0代表待审核"></el-input>
    </el-form-item>
    <el-form-item label="" prop="createBy">
      <el-input v-model="dataForm.createBy" placeholder=""></el-input>
    </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  export default {
    data () {
      return {
        visible: false,
        dataForm: {
          id: 0,
          internshipCommunity: '',
          internshipBegintime: '',
          internshipEndtime: '',
          workLocation: '',
          participants: '',
          caseName: '',
          keywords: '',
          serviceTarget: '',
          caseType: '',
          uploadresourceDate: '',
          resourceUrl: '',
          type: '',
          status: '',
          createBy: ''
        },
        dataRule: {
          internshipCommunity: [
            { required: true, message: '实习社区不能为空', trigger: 'blur' }
          ],
          internshipBegintime: [
            { required: true, message: '实习开始时间不能为空', trigger: 'blur' }
          ],
          internshipEndtime: [
            { required: true, message: '实习结束时间不能为空', trigger: 'blur' }
          ],
          workLocation: [
            { required: true, message: '工作地点不能为空', trigger: 'blur' }
          ],
          participants: [
            { required: true, message: '参与人员不能为空', trigger: 'blur' }
          ],
          caseName: [
            { required: true, message: '案例名称不能为空', trigger: 'blur' }
          ],
          keywords: [
            { required: true, message: '关键字不能为空', trigger: 'blur' }
          ],
          serviceTarget: [
            { required: true, message: '服务对象不能为空', trigger: 'blur' }
          ],
          caseType: [
            { required: true, message: '案例类型不能为空', trigger: 'blur' }
          ],
          uploadresourceDate: [
            { required: true, message: '上传资源日期不能为空', trigger: 'blur' }
          ],
          resourceUrl: [
            { required: true, message: '上传资源文件url不能为空', trigger: 'blur' }
          ],
          type: [
            { required: true, message: '1代表典型个案，2代表小组活动案例不能为空', trigger: 'blur' }
          ],
          status: [
            { required: true, message: '1代表通过审核，0代表待审核不能为空', trigger: 'blur' }
          ],
          createBy: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.id = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.id) {
            this.$http({
              url: this.$http.adornUrl(`/courseresourse/casetable/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.internshipCommunity = data.caseTable.internshipCommunity
                this.dataForm.internshipBegintime = data.caseTable.internshipBegintime
                this.dataForm.internshipEndtime = data.caseTable.internshipEndtime
                this.dataForm.workLocation = data.caseTable.workLocation
                this.dataForm.participants = data.caseTable.participants
                this.dataForm.caseName = data.caseTable.caseName
                this.dataForm.keywords = data.caseTable.keywords
                this.dataForm.serviceTarget = data.caseTable.serviceTarget
                this.dataForm.caseType = data.caseTable.caseType
                this.dataForm.uploadresourceDate = data.caseTable.uploadresourceDate
                this.dataForm.resourceUrl = data.caseTable.resourceUrl
                this.dataForm.type = data.caseTable.type
                this.dataForm.status = data.caseTable.status
                this.dataForm.createBy = data.caseTable.createBy
              }
            })
          }
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/courseresourse/casetable/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'internshipCommunity': this.dataForm.internshipCommunity,
                'internshipBegintime': this.dataForm.internshipBegintime,
                'internshipEndtime': this.dataForm.internshipEndtime,
                'workLocation': this.dataForm.workLocation,
                'participants': this.dataForm.participants,
                'caseName': this.dataForm.caseName,
                'keywords': this.dataForm.keywords,
                'serviceTarget': this.dataForm.serviceTarget,
                'caseType': this.dataForm.caseType,
                'uploadresourceDate': this.dataForm.uploadresourceDate,
                'resourceUrl': this.dataForm.resourceUrl,
                'type': this.dataForm.type,
                'status': this.dataForm.status,
                'createBy': this.dataForm.createBy
              })
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.visible = false
                    this.$emit('refreshDataList')
                  }
                })
              } else {
                this.$message.error(data.msg)
              }
            })
          }
        })
      }
    }
  }
</script>
