<template>
  <div>
    <h1 style="margin-top: 30px;">通讯录第三版</h1>
    <div style="margin-bottom: 5px;">

      <el-input v-model="name" placeholder="请输入名字" suffix-icon="el-icon-search" style="width: 200px;"
                @keyup.enter.native="loadPost"></el-input>
      <el-button type="primary" style="margin-left: 5px;" @click="loadPost">查询</el-button>

      <el-button type="primary" style="margin-left: 5px;" @click="add">新增</el-button>
    </div>
      <el-table :data="tableData" style="width: 610px;margin: auto;" height="600px" border
                :header-cell-style="{ background:'silver',color:'black','text-align':'center'}" stripe
                >

        <el-table-column prop="name" label="姓名" width="160" align="center"></el-table-column>

        <el-table-column prop="gender" label="性别" width="50" align="center"></el-table-column>

        <el-table-column prop="age" label="年龄" width="50" align="center"></el-table-column>

        <el-table-column prop="tel" label="电话" width="180" align="center"></el-table-column>

        <el-table-column prop="operate" label="操作" width="150" align="center">
          <template slot-scope="scope">
            <el-button type="primary" icon="el-icon-edit" size="small" @click="mod(scope.row)"></el-button>
            <el-popconfirm
                title="确定删除吗？"
                @confirm="del(scope.row.id)"
                style="margin-left: 5px;"
            >
              <el-button slot="reference" type="danger" icon="el-icon-delete" size="small"></el-button>
            </el-popconfirm>

          </template>

        </el-table-column>

      </el-table>

    <el-dialog
            title="提示"
            :visible.sync="centerDialogVisible"
            width="30%"
            center>

          <el-form ref="form" :rules="rules" :model="form" label-width="80px">

            <el-form-item label="名字" prop="name">
              <el-col :span="20">
                <el-input v-model="form.name"></el-input>
              </el-col>
            </el-form-item>

            <el-form-item label="性别" prop="gender">
              <el-radio-group v-model="form.gender">
                <el-radio label="男">男</el-radio>
                <el-radio label="女">女</el-radio>
              </el-radio-group>

            </el-form-item>
            <el-form-item label="年龄" prop="age">
              <el-col :span="20">
                <el-input v-model="form.age"></el-input>
              </el-col>
            </el-form-item>

            <el-form-item label="电话" prop="tel">
              <el-col :span="20">
                <el-input v-model="form.tel"></el-input>
              </el-col>
            </el-form-item>
          </el-form>
          <span slot="footer" class="dialog-footer">
        <el-button @click="centerDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </span>
        </el-dialog>
      </div>
    </template>

    <script>
      export default {
        // eslint-disable-next-line vue/multi-word-component-names
        name:"Index",

        data() {
          let checkAge = (rule, value, callback) => {
            setTimeout(() => {
                if (value < 0||value>200) {
                  callback(new Error('请输入0~200之间的数值'));
                } else {
                  callback();
                }
            }, 1000);
          };
          return {
            tableData: [],
            name:'',
            centerDialogVisible:false,
            form:{
              name:'',
              gender:'',
              age:'',
              tel:'',
              id:''
            },
            rules: {
              name: [
                { required: true, message: '请输入姓名', trigger: 'blur' },
              ],
              gender: [
                { required: true, message: '请选择性别', trigger: 'blur' },
              ],
              age: [
                { required: true, message: '请输入年龄', trigger: 'blur' },
                { validator: checkAge, trigger: 'blur' }
              ],
              tel: [
                {required: true,message: "手机号不能为空",trigger: "blur"},
                {pattern: /^1[3|4|5|6|7|8|9][0-9]\d{8}$/, message: "请输入正确的手机号码", trigger: "blur"}
              ],
            }
          }
        },

        methods:{
          loadGet(){
            this.$axios.get('http://localhost:8081/person/list',).then(res=>{
              console.log(res)
              this.tableData=res.data
            })
          },
          loadPost(){
            this.$axios.post('http://localhost:8081/person/listP',{name:this.name}).then(res=>{
              console.log(res)
              if(res.data.code){
                this.tableData=res.data.data
              }else{
                alert('获取失败')
              }
            })
          },
          add(){
            this.form={}
            this.centerDialogVisible=true
          },
          doSave(){
            this.$axios.post('http://localhost:8081/person/save',this.form).then(res=>{
              console.log(res)
              if(res){
                this.$message({
                  message: '添加成功！',
                  type: 'success'
                });
                this.centerDialogVisible = false
                this.loadPost()
                //this. resetForm()
              }else{
                this.$message({
                  message: '添加失败！',
                  type: 'error'
                });
              }

            })
          },
          doMod(){
            this.$axios.post('http://localhost:8081/person/mod',this.form).then(res=>{
              console.log(res)
              if(res){

                this.$message({
                  message: '修改成功！',
                  type: 'success'
                });
                this.centerDialogVisible = false
                this.loadPost()
                //this. resetForm()
              }else{
                this.$message({
                  message: '修改失败！',
                  type: 'error'
                });
              }

            })
          },
          save(){
            this.$refs.form.validate((valid) => {
              if (valid) {
                if(this.form.id){
                  this.doMod();
                }else{
                  this.doSave();
                }
              } else {
                console.log('error submit!!');
                return false;
              }
            });
          },
          mod(row){
            console.log(row)
            this.form.name=row.name
            this.form.gender=row.gender
            this.form.age=row.age
            this.form.tel=row.tel
            this.form.id=row.id

            this.centerDialogVisible=true
          },
          del(id){
            console.log(id)
            this.$axios.get('http://localhost:8081/person/delete?id='+id).then(res=>{
              if(res){
                this.$message({
                  message: '删除成功！',
                  type: 'error'});
                this.loadPost();
              }else{
                this.$message({
                  message: '删除失败！',
                  type: 'error'});
              }
            })
          }
        },
        beforeMount() {
          this.loadPost();
        }
    }
    </script>

    <style scoped>

    </style>