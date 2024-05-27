<script setup lang="ts">

import {useRouter} from "vue-router";
import {onMounted, ref} from "vue";
import myAxios from "../plugins/myAxios.ts";
import {showFailToast, showSuccessToast} from "vant";
import TeamCardList from "../components/TeamCardList.vue";

const router=useRouter();
const searchText =ref('');

const toAddTeam = () => {
  router.push({
    path: "/team"
  })
}

const teamList =ref([]);
/**
 * 搜索队伍
 * @param val
 * @param status
 * @returns {Promise<void>}
 */
const listTeam = async (val = '') => {
  const res = await myAxios.get("/team/list/my/join", {
    params: {
      searchText: val,
      pageNum: 1,
    },
  });
  if (res?.code === 0) {
    teamList.value = res.data;
  } else {
    showFailToast('加载队伍失败，请刷新重试');
  }
}

// 页面加载时只触发一次,查询队伍
onMounted( () => {
  listTeam();
})

//搜索
const onSearch = (val) => {
  listTeam(val);
};

</script>

<template>
  <div id="teamPage">
    <van-search v-model="searchText" placeholder="搜索队伍"  @search="onSearch" />
    <van-button type="primary" @click="toAddTeam">加入队伍</van-button>
    <team-card-list :teamList="teamList"/>
    <van-empty v-if="teamList?.length < 1" description="数据为空"/>
  </div>
</template>

<style scoped>

</style>