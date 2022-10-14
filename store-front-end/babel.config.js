/*
 * @Description:
 * @Author: chuyang
 * @Date: 2022-05-18 18:03:33
 * @LastEditors: chuyang
 * @LastEditTime: 2022-05-18 18:14:25
 * @FilePath: \demo-front-end\babel.config.js
 */
module.exports = {
  plugins: [
    [
      "import",
      {
        libraryName: "element-plus",
        customStyleName: (name) => {
          return `element-plus/lib/theme-chalk/${name}.css`;
        },
      },
    ],
  ],
};
