# Backend
2022 1학기 소프트웨어 공학 프로젝트 Backend

## 규칙

### 1. 이슈 생성

- 모든 작업은 **이슈 기반**으로 이루어집니다.
- 작업을 하기 전 반드시 이슈를 **먼저** 생성합니다.
- 이슈를 생성할 때 미리 정해진 **이슈 템플릿**을 사용합니다. 이슈 생성 시 고를 수 있습니다.
- 이슈 생성 시 알맞은 라벨을 선택합니다.
- 이슈는 **To do list** 형식으로 작성합니다.

### 2. 브랜치 생성

- 이슈 생성 후에 자신이 작업할 브랜치를 `master` 브랜치에서 분기시킵니다.
- 브랜치 명명 규칙은 아래와 같습니다.

###  3. 브랜치 명명 규칙

|내용|브랜치 이름 규칙|   
| :-: | :-: |   
|기능 수정|feature#issue number/ feature name|   
|버그 수정|bugfix#issue number/ bugfix name|   
|급한 버그 수정|hotfix#issue number/ hotfix name|   
|문서 수정|docs#issue number/ docs name|

### 4. Pull Request 생성

- `master` 브랜치에 직접 `push`하는 것은 금지합니다.
- PR의 단위는 **커밋 하나**를 권장합니다. 여러 커밋을 만들어야 할 때는 여러 PR로 나눕니다.
- PR 제목은 커밋 제목을 그대로 사용합니다.

### 5. master 브랜치 병합

- 다른 팀원들은 반드시 **변경 내역**을 꼼꼼히 확인한 후 PR을 승인합니다.
- PR을 병합할 때는 `Merge commit` 을 생성합니다.


### 6. Commit message 작성 규칙

|내용|커밋 작성 규칙|   
| :-: | :-: |   
|기능 추가|[feature] feature name|   
|버그 수정|[bugfix] bugfix name|   
|급한 버그 수정|[hotfix] hotfix name|   
|문서 수정|[docs] docs name|
|리팩토링|[refactor] refactor name|
|테스트 수정|[test] test name|
