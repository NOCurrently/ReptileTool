-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- 主机： localhost
-- 生成日期： 2021-07-02 18:47:29
-- 服务器版本： 5.7.26
-- PHP 版本： 7.3.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 数据库： `rule_platform`
--

-- --------------------------------------------------------

--
-- 表的结构 `aggregat_data_source`
--

CREATE TABLE `aggregat_data_source` (
  `id` int(11) NOT NULL,
  `process_segment_json` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `depend_aggregat_ids` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` tinyint(4) DEFAULT '1',
  `create_by` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `update_by` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- 转存表中的数据 `aggregat_data_source`
--

INSERT INTO `aggregat_data_source` (`id`, `process_segment_json`, `depend_aggregat_ids`, `status`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES
(4, '[{\"id\":6,\"sub\":[{\"id\":5}]}]', NULL, 1, 'string', 'string', '2021-06-27 14:25:35', '2021-06-27 14:30:21');

-- --------------------------------------------------------

--
-- 表的结构 `data_source`
--

CREATE TABLE `data_source` (
  `id` int(11) NOT NULL,
  `timeout` tinyint(4) DEFAULT NULL,
  `type` tinyint(4) DEFAULT '0',
  `param_template` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `result_extract` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `backup` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` tinyint(4) DEFAULT '1',
  `update_by` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `create_by` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `url` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `headers` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `request_type` tinyint(4) DEFAULT NULL,
  `data_type` tinyint(4) DEFAULT NULL,
  `service` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `method` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `token` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `alise` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- 转存表中的数据 `data_source`
--

INSERT INTO `data_source` (`id`, `timeout`, `type`, `param_template`, `result_extract`, `backup`, `status`, `update_by`, `create_by`, `create_time`, `update_time`, `url`, `headers`, `request_type`, `data_type`, `service`, `method`, `token`, `alise`) VALUES
(5, NULL, 1, '{\"name\":\"${name}\",\"age\":${age},\"address\":\"${address}\"}', '{\"namexc\":\"namexc_returt\"}', NULL, 1, 'string', 'string', '2021-06-27 14:14:18', '2021-06-27 15:03:32', 'http://127.0.0.1:5002/xc', NULL, NULL, 1, NULL, NULL, NULL, NULL),
(6, NULL, 0, '[{\"deliveryType\":${deliveryType},\"childChannel\":\"${childChannel}\",\"class\":\"com.jd.deliveryTransition.model.DeliveryDynamicFlow\"}]', '{\"errorMsg\":\"msg\"}', NULL, 1, 'string', 'string', '2021-06-27 14:21:09', '2021-06-27 14:42:25', NULL, NULL, NULL, NULL, 'com.jd.deliveryTransition.api.DeliveryTransitionAssistantContentService', 'insertContentItem', NULL, NULL);

-- --------------------------------------------------------

--
-- 表的结构 `dict_type`
--

CREATE TABLE `dict_type` (
  `id` int(11) NOT NULL,
  `type` tinyint(4) DEFAULT '0',
  `code` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `remarks` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` tinyint(4) DEFAULT '1',
  `update_by` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `create_by` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- 转存表中的数据 `dict_type`
--

INSERT INTO `dict_type` (`id`, `type`, `code`, `name`, `remarks`, `status`, `update_by`, `create_by`, `create_time`, `update_time`) VALUES
(1, 0, 'A', '请求例子', '2', 1, 'string', 'string', '2021-07-02 18:43:01', '2021-07-02 18:44:54');

-- --------------------------------------------------------

--
-- 表的结构 `dict_value`
--

CREATE TABLE `dict_value` (
  `id` int(11) NOT NULL,
  `type_code` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `remarks` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` tinyint(4) DEFAULT '1',
  `update_by` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `create_by` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `value_str` varchar(200) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- 转存表中的数据 `dict_value`
--

INSERT INTO `dict_value` (`id`, `type_code`, `name`, `sort`, `remarks`, `status`, `update_by`, `create_by`, `create_time`, `update_time`, `value_str`) VALUES
(1, 'A', 'name', 0, '00', 1, NULL, NULL, '2021-07-02 18:44:02', '2021-07-02 18:45:24', 'xiaochao');

-- --------------------------------------------------------

--
-- 表的结构 `operation_log`
--

CREATE TABLE `operation_log` (
  `id` int(11) NOT NULL,
  `type` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `create_by` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `url` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `operation_data` text COLLATE utf8_unicode_ci
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- 转存表中的数据 `operation_log`
--

INSERT INTO `operation_log` (`id`, `type`, `create_by`, `url`, `create_time`, `operation_data`) VALUES
(1, 'execute', NULL, 'com.xc.datasouce.controller.AggregatDataSourceController#execute', '2021-06-27 15:19:36', '{\"result\":{\"msg\":\"dsafdsa\",\"namexc_returt\":\"222xc\"},\"param\":[{\"id\":4,\"param\":{\"name\":\"222\",\"age\":3,\"address\":\"发\",\"deliveryType\":1,\"childChannel\":\"channel\"}}]}');

-- --------------------------------------------------------

--
-- 表的结构 `rule`
--

CREATE TABLE `rule` (
  `id` int(11) NOT NULL,
  `name` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `type` tinyint(4) DEFAULT NULL,
  `sort` int(11) NOT NULL DEFAULT '0',
  `expression_json_list` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `executive_logic` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `action_json_list` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `aggregat_id` int(11) DEFAULT NULL,
  `expect_return_class` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` tinyint(4) NOT NULL DEFAULT '1',
  `update_by` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `create_by` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- 转存表中的数据 `rule`
--

INSERT INTO `rule` (`id`, `name`, `type`, `sort`, `expression_json_list`, `executive_logic`, `action_json_list`, `aggregat_id`, `expect_return_class`, `status`, `update_by`, `create_by`, `create_time`, `update_time`) VALUES
(1, '111', NULL, 0, '[{\"left\":\"namexc_returt\",\"operation\":\"!=\",\"right\":\"1\"},{\"left\":\"msg\",\"operation\":\"==\",\"right\":\"100\"}]', '#1&&#2', '[{\"topic\":\"xiaochao\",\"message\":\"fdafdsaf\"}]', 4, 'java.lang.Boolean', 1, 'string', 'string', '2021-06-29 22:32:31', '2021-07-02 17:02:37');

--
-- 转储表的索引
--

--
-- 表的索引 `aggregat_data_source`
--
ALTER TABLE `aggregat_data_source`
  ADD PRIMARY KEY (`id`);

--
-- 表的索引 `data_source`
--
ALTER TABLE `data_source`
  ADD PRIMARY KEY (`id`);

--
-- 表的索引 `dict_type`
--
ALTER TABLE `dict_type`
  ADD PRIMARY KEY (`id`);

--
-- 表的索引 `dict_value`
--
ALTER TABLE `dict_value`
  ADD PRIMARY KEY (`id`);

--
-- 表的索引 `operation_log`
--
ALTER TABLE `operation_log`
  ADD PRIMARY KEY (`id`);

--
-- 表的索引 `rule`
--
ALTER TABLE `rule`
  ADD PRIMARY KEY (`id`);

--
-- 在导出的表使用AUTO_INCREMENT
--

--
-- 使用表AUTO_INCREMENT `aggregat_data_source`
--
ALTER TABLE `aggregat_data_source`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- 使用表AUTO_INCREMENT `data_source`
--
ALTER TABLE `data_source`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- 使用表AUTO_INCREMENT `dict_type`
--
ALTER TABLE `dict_type`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- 使用表AUTO_INCREMENT `dict_value`
--
ALTER TABLE `dict_value`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- 使用表AUTO_INCREMENT `operation_log`
--
ALTER TABLE `operation_log`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- 使用表AUTO_INCREMENT `rule`
--
ALTER TABLE `rule`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
