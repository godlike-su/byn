CREATE TABLE fileUpload  (
    `id` int NOT NULL,
    `name` varchar(255) NOT NULL COMMENT '原文件名称',
    `url` varchar(255) NOT NULL COMMENT '保存地址',
    `createTime` datetime NOT NULL COMMENT '放入时间',
    `profilx` varchar(255) NOT NULL COMMENT '文件类型',
    PRIMARY KEY (`id`)
);