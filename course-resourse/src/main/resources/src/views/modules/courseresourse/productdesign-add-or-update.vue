<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="设计名称" prop="designName">
      <el-input v-model="dataForm.designName" placeholder="设计名称"></el-input>
    </el-form-item>
    <el-form-item label="关键字" prop="keywords">
      <el-input v-model="dataForm.keywords" placeholder="关键字"></el-input>
    </el-form-item>
    <el-form-item label="服务对象" prop="serviceTarget">
      <el-input v-model="dataForm.serviceTarget" placeholder="服务对象"></el-input>
    </el-form-item>
    <el-form-item label="产品类别" prop="productCategory">
      <el-input v-model="dataForm.productCategory" placeholder="产品类别"></el-input>
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
          id: 0,
          designName: '',
          keywords: '',
          serviceTarget: '',
          productCategory: '',
          instructor: '',
          uploadresourceDate: '',
          resourceUrl: '',
          status: '',
          createBy: ''
        },
        dataRule: {
          designName: [
            { required: true, message: '设计名称不能为空', trigger: 'blur' }
          ],
          keywords: [
            { required: true, message: '关键字不能为空', trigger: 'blur' }
          ],
          serviceTarget: [
            { required: true, message: '服务对象不能为空', trigger: 'blur' }
          ],
          productCategory: [
            { required: true, message: '产品类别不能为空', trigger: 'blur' }
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
        this.dataForm.id = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.id) {
            this.$http({
              url: this.$http.adornUrl(`/courseresourse/productdesign/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.designName = data.productDesign.designName
                this.dataForm.keywords = data.productDesign.keywords
                this.dataForm.serviceTarget = data.productDesign.serviceTarget
                this.dataForm.productCategory = data.productDesign.productCategory
                this.dataForm.instructor = data.productDesign.instructor
                this.dataForm.uploadresourceDate = data.productDesign.uploadresourceDate
                this.dataForm.resourceUrl = data.productDesign.resourceUrl
                this.dataForm.status = data.productDesign.status
                this.dataForm.createBy = data.productDesign.createBy
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
              url: this.$http.adornUrl(`/courseresourse/productdesign/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'designName': this.dataForm.designName,
                'keywords': this.dataForm.keywords,
                'serviceTarget': this.dataForm.serviceTarget,
                'productCategory': this.dataForm.productCategory,
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
