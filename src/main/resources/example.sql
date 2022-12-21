CREATE TABLE `transaction`.`User`
(
    `id`   bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '名字',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;