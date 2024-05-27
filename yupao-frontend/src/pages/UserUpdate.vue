<script setup lang="ts">
import {useRouter} from "vue-router";
import {onMounted, ref} from "vue";
import myAxios from "../plugins/myAxios";
import {showSuccessToast, Toast} from "vant";
import {getCurrentUser} from "../services/user.ts";
//import {getCurrentUser} from "../services/user";

// const user = ref({
//   id: 1,
//   userName: 'zbx001',
//   userAccount: 'hzz',
//   gender: '男',
//   phone: '12345',
//   email: '1253728@qq.com',
//   planetCode: '123',
//   avatar:
//       'https://img2.baidu.com/it/u=1790834130,1952230725&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500',
//   gmtCreate: new Date().toDateString()
// })

const user = ref();
const router = useRouter();

/*
onMounted(async () => {
  user.value = await getCurrentUser();
})
 */

onMounted(async () =>{
  const res = await getCurrentUser();
  if (res){
    user.value = res;
    showSuccessToast('获取用户信息成功');
  } else {
    showSuccessToast('获取用户信息失败')
  }
})

const toEdit = (editKey: string, editName: string, currentValue: string) => {
  router.push({
    path: '/user/edit',
    query: {
      editKey,
      editName,
      currentValue,
    }
  })
}
</script>

<template>
  <template v-if="user">
    <van-cell title="昵称" is-link to="/user/edit" :value="user.username" @click="toEdit('username','昵称',user.username)"/>
    <van-cell title="账户" :value="user.userAccount" />
    <van-cell title="头像" is-link to="/user/edit" >
      <img style="height: 48px" :src="user.avatarUrl">
    </van-cell>
    <van-cell title="性别" is-link to="/user/edit" :value="user.gender" @click="toEdit('gender','性别',user.gender)" />
    <van-cell title="电话" is-link to="/user/edit" :value="user.phone" @click="toEdit('phone','电话',user.phone)"/>
    <van-cell title="邮箱" is-link to="/user/edit" :value="user.email" @click="toEdit('email','邮箱',user.email)"/>
    <van-cell title="星球编号" :value="user.planetCode" />
    <van-cell title="注册时间" :value="user.createTime"  />
  </template>
</template>

<style scoped>

</style>