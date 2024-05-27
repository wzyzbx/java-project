<script setup lang="ts">

import {ref} from "vue";
import Team from "../pages/Team.vue";
import {showToast, Toast} from "vant";
import Index from "../pages/Index.vue";
import {useRouter} from "vue-router";
import routes from "../config/route.ts";

const router = useRouter();
const DEFAULT_TITLE='匹配小伙伴';
const title = ref(DEFAULT_TITLE);

/**
 * 根据路由切换标题
 */
router.beforeEach((to, from) => {
  const toPath = to.path;
  const route = routes.find((route) => {
    return toPath == route.path;
  })
  title.value = route?.title ?? DEFAULT_TITLE;
})

const onClickLeft = () => router.back();
const onClickRight = () => {
  router.push('/search');
};

//const active = ref("index");
//const onChange = (index) => showToast(`标签 ${index}`);

</script>

<template>
  <van-nav-bar
      :title="title"
      left-arrow
      @click-left="onClickLeft"
      @click-right="onClickRight"
  >
  <template #right>
    <van-icon name="search" size="18" />
  </template>
  </van-nav-bar>

  <div id="content">
    <router-view/>
  </div>

  <van-tabbar route >
    <van-tabbar-item to="/" icon="home-o" name="index">主页</van-tabbar-item>
    <van-tabbar-item to="/team" icon="search" name="team">队伍</van-tabbar-item>
    <van-tabbar-item to="/user" icon="friends-o" name="user">个人</van-tabbar-item>
  </van-tabbar>




</template>

<style scoped>
#content{
  padding-bottom: 50px;
}
</style>