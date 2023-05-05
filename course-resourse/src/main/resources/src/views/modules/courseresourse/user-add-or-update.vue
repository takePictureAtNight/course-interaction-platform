<template>
  <el-dialog
    :title="!dataForm.userId ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="用户名" prop="username">
      <el-input v-model="dataForm.username" placeholder="用户名"></el-input>
    </el-form-item>
    <el-form-item label="密码" prop="passsword">
      <el-input v-model="dataForm.passsword" placeholder="密码"></el-input>
    </el-form-item>
    <el-form-item label="真实姓名" prop="realName">
      <el-input v-model="dataForm.realName" placeholder="真实姓名"></el-input>
    </el-form-item>
    <el-form-item label="学号/教师号" prop="number">
      <el-input v-model="dataForm.number" placeholder="学号/教师号"></el-input>
    </el-form-item>
    <el-form-item label="联系方式" prop="phone">
      <el-input v-model="dataForm.phone" placeholder="联系方式"></el-input>
    </el-form-item>
    <el-form-item label="创建时间" prop="createTime">
      <el-input v-model="dataForm.createTime" placeholder="创建时间"></el-input>
    </el-form-item>
    <el-form-item label="00 系统用户 01 注册用户" prop="userType">
      <el-input v-model="dataForm.userType" placeholder="00 系统用户 01 注册用户"></el-input>
    </el-form-item>
    <el-form-item label="0代表未审核 1代表已审核" prop="status">
      <el-input v-model="dataForm.status" placeholder="0代表未审核 1代表已审核"></el-input>
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
          userId: 0,
          username: '',
          passsword: '',
          realName: '',
          number: '',
          phone: '',
          createTime: '',
          userType: '',
          status: ''
        },
        dataRule: {
          username: [
            { required: true, message: '用户名不能为空', trigger: 'blur' }
          ],
          passsword: [
            { required: true, message: '密码不能为空', trigger: 'blur' }
          ],
          realName: [
            { required: true, message: '真实姓名不能为空', trigger: 'blur' }
          ],
          number: [
            { required: true, message: '学号/教师号不能为空', trigger: 'blur' }
          ],
          phone: [
            { required: true, message: '联系方式不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '创建时间不能为空', trigger: 'blur' }
          ],
          userType: [
            { required: true, message: '00 系统用户 01 注册用户不能为空', trigger: 'blur' }
          ],
          status: [
            { required: true, message: '0代表未审核 1代表已审核不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.userId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.userId) {
            this.$http({
              url: this.$http.adornUrl(`/courseresourse/user/info/${this.dataForm.userId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.username = data.user.username
                this.dataForm.passsword = data.user.passsword
                this.dataForm.realName = data.user.realName
                this.dataForm.number = data.user.number
                this.dataForm.phone = data.user.phone
                this.dataForm.createTime = data.user.createTime
                this.dataForm.userType = data.user.userType
                this.dataForm.status = data.user.status
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
              url: this.$http.adornUrl(`/courseresourse/user/${!this.dataForm.userId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'userId': this.dataForm.userId || undefined,
                'username': this.dataForm.username,
                'passsword': this.dataForm.passsword,
                'realName': this.dataForm.realName,
                'number': this.dataForm.number,
                'phone': this.dataForm.phone,
                'createTime': this.dataForm.createTime,
                'userType': this.dataForm.userType,
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
