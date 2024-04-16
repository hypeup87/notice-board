package com.board.notice.constant;

public class NoticeBoardConfigConst {
    public static final String DATASOURCE = " noticeSource";

    public class JPA {
        public static final String ENTITY_MANAGER_FACTORY_REF = " noticeJpaEntityManagerFactory";
        public static final String TRANSACTION_MANAGER_REF = " noticeTransactionManager";
    }

    public class EnvPath {
        private static final String basePackage = "com.board.notice";
        public static final String REPOSITORY_PACKAGE = basePackage + ".repository";
        public static final String ENTITY_PACKAGE = basePackage + ".entity";
    }

    public class ApplicationConf {
        public static final String DATASOURCE = "spring.datasource";
    }
}
