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
    <van-cell title="昵称" :value="user.username" />
    <van-cell title="账户" :value="user.userAccount" />
    <van-cell title="头像" >
      <img style="height: 48px" :src="user.avatarUrl">
    </van-cell>
    <van-cell title="修改信息" is-link to="/user/update" />
    <van-cell title="我创建的队伍" is-link to="/user/team/create"/>
    <van-cell title="我加入的队伍" is-link to="/user/team/join"/>
  </template>
</template>

<style scoped>

</style>