<script setup>
import {onMounted, ref} from 'vue';
import {useRoute} from "vue-router";
import myAxios from "../plugins/myAxios.ts";
import {showSuccessToast, Toast} from "vant";
import qs from 'qs';
import UserCardList from "../components/UserCardList.vue";


const route = useRoute();
const {tags} = route.query;
const userList = ref([]);
const loading = ref(true);

onMounted(async () => {
  loading.value = true;
  const userListData = await myAxios.get('/user/search/tags', {
    params: {
      tagNameList: tags
    },
    paramsSerializer: params => {
      return qs.stringify(params, {indices: false})
    }
  })
      .then(function (response) {
        console.log('/user/search/tags', response);
        //showSuccessToast('请求成功');
        return response?.data;
      })
      .catch(function (error) {
        console.error('/user/search/tags', error);
        showSuccessToast('请求失败');
      })
  console.log(userListData)
  if (userListData) {
    userListData.forEach(user => {
      if (user.tags) {
        user.tags = JSON.parse(user.tags);
      }
    })
    userList.value = userListData;
  }
  loading.value = false;
})



// const mockUser = {
//   id: 12345,
//   username: 'zbx',
//   userAccount: '12314',
//   profile: '一名精神小伙，目前还有头发，谢谢大家，阿爸爸阿爸爸阿巴阿巴阿巴',
//   avatarUrl: 'https://img2.baidu.com/it/u=1790834130,1952230725&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500',
//   gender: 0,
//   phone: '13113113111',
//   email: '592342843721987@xzcxzczxcz.com',
//   userRole: 0,
//   planetCode: '1234',
//   tags: ['java', 'emo', '打工中', 'emo', '打工中'],
//   createTime: new Date(),
// }

</script>

<template>
  <user-card-list :user-list = "userList" :loading="loading" />
  <van-empty v-if="!userList||userList.length<1" description="搜索结果为空"/>
</template>

<style scoped>

</style>