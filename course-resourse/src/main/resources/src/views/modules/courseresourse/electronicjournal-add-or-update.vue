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
    <el-form-item label="篇名" prop="title">
      <el-input v-model="dataForm.title" placeholder="篇名"></el-input>
    </el-form-item>
    <el-form-item label="关键词" prop="keywords">
      <el-input v-model="dataForm.keywords" placeholder="关键词"></el-input>
    </el-form-item>
    <el-form-item label="服务对象" prop="serviceTarget">
      <el-input v-model="dataForm.serviceTarget" placeholder="服务对象"></el-input>
    </el-form-item>
    <el-form-item label="上传资源日期" prop="uploadresourceDate">
      <el-input v-model="dataForm.uploadresourceDate" placeholder="上传资源日期"></el-input>
    </el-form-item>
    <el-form-item label="上传资源文件url" prop="resourceUrl">
      <el-input v-model="dataForm.resourceUrl" placeholder="上传资源文件url"></el-input>
    </el-form-item>
    <el-form-item label="1代表个人电子期刊，2代表小组电子期刊" prop="type">
      <el-input v-model="dataForm.type" placeholder="1代表个人电子期刊，2代表小组电子期刊"></el-input>
    </el-form-item>
    <el-form-item label="电子期刊上传者id" prop="createBy">
      <el-input v-model="dataForm.createBy" placeholder="电子期刊上传者id"></el-input>
    </el-form-item>
    <el-form-item label="1代表通过审核，0代表待审核" prop="status">
      <el-input v-model="dataForm.status" placeholder="1代表通过审核，0代表待审核"></el-input>
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
          title: '',
          keywords: '',
          serviceTarget: '',
          uploadresourceDate: '',
          resourceUrl: '',
          type: '',
          createBy: '',
          status: ''
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
          title: [
            { required: true, message: '篇名不能为空', trigger: 'blur' }
          ],
          keywords: [
            { required: true, message: '关键词不能为空', trigger: 'blur' }
          ],
          serviceTarget: [
            { required: true, message: '服务对象不能为空', trigger: 'blur' }
          ],
          uploadresourceDate: [
            { required: true, message: '上传资源日期不能为空', trigger: 'blur' }
          ],
          resourceUrl: [
            { required: true, message: '上传资源文件url不能为空', trigger: 'blur' }
          ],
          type: [
            { required: true, message: '1代表个人电子期刊，2代表小组电子期刊不能为空', trigger: 'blur' }
          ],
          createBy: [
            { required: true, message: '电子期刊上传者id不能为空', trigger: 'blur' }
          ],
          status: [
            { required: true, message: '1代表通过审核，0代表待审核不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/courseresourse/electronicjournal/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.internshipCommunity = data.electronicJournal.internshipCommunity
                this.dataForm.internshipBegintime = data.electronicJournal.internshipBegintime
                this.dataForm.internshipEndtime = data.electronicJournal.internshipEndtime
                this.dataForm.workLocation = data.electronicJournal.workLocation
                this.dataForm.title = data.electronicJournal.title
                this.dataForm.keywords = data.electronicJournal.keywords
                this.dataForm.serviceTarget = data.electronicJournal.serviceTarget
                this.dataForm.uploadresourceDate = data.electronicJournal.uploadresourceDate
                this.dataForm.resourceUrl = data.electronicJournal.resourceUrl
                this.dataForm.type = data.electronicJournal.type
                this.dataForm.createBy = data.electronicJournal.createBy
                this.dataForm.status = data.electronicJournal.status
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
              url: this.$http.adornUrl(`/courseresourse/electronicjournal/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'internshipCommunity': this.dataForm.internshipCommunity,
                'internshipBegintime': this.dataForm.internshipBegintime,
                'internshipEndtime': this.dataForm.internshipEndtime,
                'workLocation': this.dataForm.workLocation,
                'title': this.dataForm.title,
                'keywords': this.dataForm.keywords,
                'serviceTarget': this.dataForm.serviceTarget,
                'uploadresourceDate': this.dataForm.uploadresourceDate,
                'resourceUrl': this.dataForm.resourceUrl,
                'type': this.dataForm.type,
                'createBy': this.dataForm.createBy,
                'status': this.dataForm.status
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
