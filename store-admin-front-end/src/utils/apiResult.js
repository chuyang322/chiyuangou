/*
 * @Description:
 * @Author: chuyang
 * @Date: 2022-06-05 21:36:55
 * @LastEditors: chuyang
 * @LastEditTime: 2022-07-06 15:55:43
 * @FilePath: \store-admin-front-end\src\utils\apiResult.js
 */
import { ElMessage } from "element-plus";

export const apiResultParse = (data) => {
  if (!data || data === "") {
    ElMessage.error("请求失败，请重新登录后尝试");
    return false;
  } else if (data.code === 200) {
    if (data.message) {
      ElMessage.success(data.message);
    }
    return true;
  } else if (data.code === 400) {
    ElMessage.error(data.message);
    return false;
  }
};
