CREATE TABLE `user` (
   `id` bigint NOT NULL,
   `userName` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
   `password` varchar(36) DEFAULT NULL,
   `nickName` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
   `realName` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
   `birthday` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
   `sex` int DEFAULT NULL,
   `email` varchar(36) DEFAULT NULL,
   `mobile` varchar(36) DEFAULT NULL,
   `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
   `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
   `isDelete` tinyint(1) DEFAULT NULL,
   PRIMARY KEY (`id`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

 insert into `user` (`id`, `userName`, `password`, `nickName`, `realName`, `birthday`, `sex`, `email`, `mobile`, `createTime`, `updateTime`, `isDelete`) values('1','naikuo','123456','nai','kuo','2020-05-25 17:21:15','1','8242@qq.com','132','2020-05-25 17:21:15','2020-05-25 17:21:15','1');
