<!--
 * @Description: 
 * @Author: chuyang
 * @Date: 2022-07-02 18:00:10
 * @LastEditors: chuyang
 * @LastEditTime: 2022-07-07 17:21:17
 * @FilePath: \store-admin-front-end\src\components\UserManage\UserManage.vue
-->

<template>
  <div class="user-manage">
    <table border="1">
      <tr>
        <th>id</th>
        <th>头像</th>
        <th>用户名</th>
        <th>密码</th>
        <th>性别</th>
        <th>邮箱</th>
        <th>手机</th>
        <th>创建时间</th>
        <th>操作</th>
      </tr>
      <tr v-for="user in userList.users"
          :key="user.id">
        <td>{{user.id}}</td>
        <td><img :src="user.avatar"
               alt=""></td>
        <td>{{user.name}}</td>
        <td>{{user.password}}</td>
        <td>{{user.sex}}</td>
        <td>{{user.email}}</td>
        <td>{{user.phone}}</td>
        <td>{{user.createTime}}</td>
        <td>
          <el-button type="danger"
                     @click="handleDeleteUser(user.id)">删除</el-button>
        </td>
      </tr>
    </table>
    <div class="page">
      <el-pagination background
                     layout="prev, pager, next"
                     :total="userList.total"
                     :page-size="userList.pageSize"
                     :current-page="current"
                     @current-change="handleCurrentChange" />
    </div>
  </div>
</template>

<script>
import { deleteUser, getUsers } from '@/service/user'
import { apiResultParse } from '@/utils/apiResult'
import { mapActions, mapState } from 'vuex'
export default {
  name: 'v-user-manage',
  data () {
    return {
      current: 1
    }
  },
  computed: {
    ...mapState([
      'userList'
    ]),
  },
  methods: {
    ...mapActions([
      'userList/updateUsers',
      'userList/updatePages',
      'userList/updateTotal',
      'userList/updateCurrent'
    ]),
    handleCurrentChange: async function (current) {
      this.current = current
      var data = await getUsers(current, this.userList.pageSize)
      if (apiResultParse(data)) {
        this['userList/updateUsers']({ users: data.data.records })
        this['userList/updatePages']({ pages: data.data.pages })
        this['userList/updateTotal']({ total: data.data.total })
        this['userList/updateCurrent']({ current: data.data.current })
      }
    },
    handleDeleteUser: async function (id) {
      var data = await deleteUser(id)
      apiResultParse(data)
      this.initUserList()
    },
    initUserList: async function () {
      var data = await getUsers(1, this.userList.pageSize)
      if (apiResultParse(data)) {
        this['userList/updateUsers']({ users: data.data.records })
        this['userList/updatePages']({ pages: data.data.pages })
        this['userList/updateTotal']({ total: data.data.total })
        this['userList/updateCurrent']({ current: data.data.current })
      }
    }
  },
  mounted () {
    this.initUserList()
  }
}
</script>
<style scoped>
.user-manage {
  width: 100%;
}
.page {
  text-align: center;
}
table {
  border-collapse: collapse;
  border-color: #2e0561;
  width: 100%;
}
th {
  padding: 10px;
  background-color: #545c64;
  color: #fff;
  font-weight: 300;
  text-align: center;
}
td {
  padding: 10px;
  max-width: 100px;
  overflow-y: hidden;
  overflow-x: inherit;
  text-align: center;
}

td::-webkit-scrollbar {
  display: none;
}
img {
  height: 64px;
  width: 64px;
}
</style>