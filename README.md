# 공지사항 게시판 API #
> 공지사항 등록 / 수정 / 삭제 / 조회 기능을 지원 하는 API입니다.

### 프로젝트 환경구성 ###
* jdk 17
* SpringBoot 3.2.4
* Gradle 7.3.3
* mysql
* JPA / QueryDSL

### 실행방법 ###
* [table.ddl](src%2Fmain%2Fresources%2Fsql%2Ftable.ddl) 파일로 테이블을 생성합니다.
* [application.yml](src%2Fmain%2Fresources%2Fapplication.yml) 파일의 local-sql-ip / local-sql-username / local-sql-password 를 통해 mysql 접속 정보를 설정합니다.
* gradle clean / build 를 통해 [build.gradle](build.gradle)의 설정된 QueryDSL를 통해 Generate Class를 생성합니다.

### 핵심 문제해결 전략 ###
* 기본적인 쿼리는 JPA / QueryDSL를 통해 처리합니다.
* 조회 대상의 대용량 트레픽을 고려하여 조회수 업데이트를 Async로 처리합니다.
* 파일업로드 요청 시 중복된 파일명을 업로드 할 경우 파일명 + 시간을 통해 중복을 방지합니다.

### postman sample ###
* [공지사항.postman_collection.json](src%2Fmain%2Fresources%2Fpostman-test%2F%EA%B3%B5%EC%A7%80%EC%82%AC%ED%95%AD.postman_collection.json)

### 기능 확인을 위한 CURL sample ###
CURL을 통해 API를 호출하여 테스트합니다.
* 공지사항 조회
  > curl --location 'http://localhost/board/notice/{id}'

* 공지사항 등록
  >  curl --location 'http://localhost/board/notice' \
  --form 'title="공지사항 테스트 입니다."' \
  --form 'contents="등록된 공지사항을 확인해주세요!"' \
  --form 'startDate="2024-04-15T00:00"' \
  --form 'endDate="2024-04-25T00:00"' \
  --form 'files=@"/${path}/test.txt"' \
  --form 'files=@"/${path}/test2.txt"' 
* 공지사항 수정
  > curl --location --request PATCH 'http://localhost/board/notice/{id}' \
  --form 'title="제목변경 33"' \
  --form 'contents="내용변경 33"' \
  --form 'startDate="2024-04-16T09:00"' \
  --form 'endDate="2024-04-26T09:00"' \
  --form 'files=@"/${path}/test.txt"' \
  --form 'files=@"/${path}/test2.txt"'

* 공지사항 삭제
  > curl --location --request DELETE 'http://localhost/board/notice/{id}'
