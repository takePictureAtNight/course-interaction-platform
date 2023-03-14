<template>
  <el-dialog
    :title="!dataForm.int ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="项目名称" prop="projectName">
      <el-input v-model="dataForm.projectName" placeholder="项目名称"></el-input>
    </el-form-item>
    <el-form-item label="关键词" prop="keywords">
      <el-input v-model="dataForm.keywords" placeholder="关键词"></el-input>
    </el-form-item>
    <el-form-item label="项目类别" prop="projectCategory">
      <el-input v-model="dataForm.projectCategory" placeholder="项目类别"></el-input>
    </el-form-item>
    <el-form-item label="指导老师" prop="instructor">
      <el-input v-model="dataForm.instructor" placeholder="指导老师"></el-input>
    </el-form-item>
    <el-form-item label="上传资料日期" prop="uploadresourceDate">
      <el-input v-model="dataForm.uploadresourceDate" placeholder="上传资料日期"></el-input>
    </el-form-item>
    <el-form-item label="上传资源文件url" prop="resourceUrl">
      <el-input v-model="dataForm.resourceUrl" placeholder="上传资源文件url"></el-input>
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
          int: 0,
          projectName: '',
          keywords: '',
          projectCategory: '',
          instructor: '',
          uploadresourceDate: '',
          resourceUrl: '',
          status: '',
          createBy: ''
        },
        dataRule: {
          projectName: [
            { required: true, message: '项目名称不能为空', trigger: 'blur' }
          ],
          keywords: [
            { required: true, message: '关键词不能为空', trigger: 'blur' }
          ],
          projectCategory: [
            { required: true, message: '项目类别不能为空', trigger: 'blur' }
          ],
          instructor: [
            { required: true, message: '指导老师不能为空', trigger: 'blur' }
          ],
          uploadresourceDate: [
            { required: true, message: '上传资料日期不能为空', trigger: 'blur' }
          ],
          resourceUrl: [
            { required: true, message: '上传资源文件url不能为空', trigger: 'blur' }
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
        this.dataForm.int = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.int) {
            this.$http({
              url: this.$http.adornUrl(`/courseresourse/projectreport/info/${this.dataForm.int}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.projectName = data.projectReport.projectName
                this.dataForm.keywords = data.projectReport.keywords
                this.dataForm.projectCategory = data.projectReport.projectCategory
                this.dataForm.instructor = data.projectReport.instructor
                this.dataForm.uploadresourceDate = data.projectReport.uploadresourceDate
                this.dataForm.resourceUrl = data.projectReport.resourceUrl
                this.dataForm.status = data.projectReport.status
                this.dataForm.createBy = data.projectReport.createBy
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
              url: this.$http.adornUrl(`/courseresourse/projectreport/${!this.dataForm.int ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'int': this.dataForm.int || undefined,
                'projectName': this.dataForm.projectName,
                'keywords': this.dataForm.keywords,
                'projectCategory': this.dataForm.projectCategory,
                'instructor': this.dataForm.instructor,
                'uploadresourceDate': this.dataForm.uploadresourceDate,
                'resourceUrl': this.dataForm.resourceUrl,
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
