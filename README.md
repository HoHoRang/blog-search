# 프로젝트 개요

## 블로그 검색 서비스
블로그 검색 서비스

1. 블로그 검색 기능
2. 블로그 인기 검색어 목록

# API 명세

[blog-search API 명세서.xlsx](https://github.com/HoHoRang/blog-search/files/11039379/blog-search.API.xlsx)
![image](https://user-images.githubusercontent.com/15374108/226888559-c00338fc-2137-4859-83fb-62c50030e3d8.png)

### 블로그 검색 리스트("/search/list", GET)
키워드를 통해 블로그를 검색할 수 있습니다.

- 카카오 블로그 검색 API, 네이버 블로그 검색 API 활용
- 카카오 블로그 검색 API에 장애 발생한 경우, 네이버 블로그 검색 API 사용하도록 구현
- 검색 소스가 추가될 것을 고려하여, SpringConfig에 Service를 Bean으로 등록하도록 구현
- Parameter는 아래와 같음
```
query:  [Required] 검색어
sort:   [Optional] 정렬 방식(accuracy:정확도, recency:최신순)
page:   [Optional] 결과 페이지 번호(기본 1)
size:   [Optional] 한 페이지 문서 수(기본 10)
```


### 블로그 인기 검색어("/search/top-keywords", GET)
사용자들이 많이 검색한 상위 10개의 검색 키워드를 조회

![image](https://user-images.githubusercontent.com/15374108/226824957-e385a18f-5eb0-4ccb-b8f5-1e525552b95c.png)

- 검색어 별로 검색한 횟수도 함께 조회

# 빌드 결과물(.jar)

- [파일 링크](https://drive.google.com/file/d/1XYUgLx8nTzqUBwb0NlQ0gXfN4hymxfes/view?usp=sharing)

# 참고사항

- src/main/resources 디렉토리에 application.properties 파일이 있어야 정상 빌드

![image](https://user-images.githubusercontent.com/15374108/226827177-f6987538-8054-4a25-b4e6-d59e7414e625.png)

- DB는 h2 인메모리 DB로 구현했습니다. 아래와 같이 {URL}/h2-console로 접근 가능
```
Driver Class: org.h2.Driver
JDBC URL: jdbc:h2:mem:test
User Name: sa
```

![image](https://user-images.githubusercontent.com/15374108/226860634-96ae44e3-0a0f-48a3-bc87-5fa964c6f7fe.png)