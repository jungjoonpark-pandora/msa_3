# Build Jenkins Pipeline

## 개요

- Jenkins Pipeline을 활용하여 애플리케이션의 테스트 및 빌드, 배포의 자동화된 환경 구축


## 목표

- Jenkins Pipeline을 활용한 CI/CD 자동화 구축
- Jenkins의 동작 원리에 대한 이해
- Jenkins Pipeline DSL에 대한 이해


## 준비 사항
- Local Jenkins Master Server (Native or Docker)
- Application (MSA or Simple App)
  - Mono Repo vs. Multi Repo
- [Project #2](https://github.com/coupang-edu/a4e-msa-project-02)에서 작성한 Docker 기반의 Application 실행 환경


## 요구 사항

- Application의 Test, Build 그리고 Deploy를 수행하는 **Jenkins Pipeline**의 구축
- Pipeline 구축 단계 및 전체적인 구조에 대해 설명하는 **README.md** 작성


### 0. 주의 사항

- 아래의 명세에 명시되지 않은 내용은 **자유롭게** 구현


### 1. Repository Setup - Spring Boot Application

- Jenkins를 활용하여 Test 및 Build를 수행할 MSA Application의 Repository를 설정
  - Service별 각각의 Repository vs. Application 전체에 대한 단일 Repository
  - 만들고자 하는 Jenkins Pipeline 구조에 따라 선택하여 구성
- MSA를 구성하는 6~7개의 서비스가 Local 성능 상의 이슈로 온전히 동작하기 힘든 경우, Monolithic Architecture의 Simple Application을 선택적으로 사용


### 2. Jenkins Pipeline (Test & Build) - Jenkinsfile

- Repository로부터 받아온 Spring Boot Application의 Test 및 Build 수행


### 3. Jenkins Pipeline (Docker) - Jenkinsfile

- Application의 Docker Image Build
  - Docker Image Versioning을 고려하여 구성
- 생성된 Docker Image를 Docker Hub, AWS ECR, Azure Container Registry 등의 Docker Registry에 등록
- 새롭게 Build된 Image를 사용하여 Local에서 동작하고 있는 Application을 Update


### 4. GitHub Webhook 설정 (선택)

- GitHub Webhook을 통해 Local Jenkins Server의 Pipeline Job이 수행되도록 하는 trigger 설정 ([ngrok](https://ngrok.com/) 등의 툴 활용)


## 제출 방법

- Git을 통해 프로젝트 URL의 GitHub repository를 로컬로 clone

    ```bash
    $ git clone https://github.com/coupang-edu/a4e-msa-project-03
    $ cd a4e-msa-project-03
    ```

- 본인의 `사내 이메일의 아이디 부분`으로 branch 생성 및 이동

    ```bash
    $ git switch -c john
    ```

- 코드 작성이 완료된 프로젝트를, 아래와 같이 프로젝트 폴더명을 본인의 영문명으로 변경 (폴더 구조는 자유롭게 구성)
  - Application 코드를 제외한 Jenkins Pipeline Script와 README를 제출
  - 추가적으로 Pipeline 구성을 위하여 변경한 Docker 및 Application 설정이 있다면, 관련 파일을 함께 제출하거나 README에 작성

    ```bash
    README.md
    john/
        ├── Jenkinsfile
        └── README.md
    ```

- 코드 작성 완료 후, 해당 branch를 push

    ```bash
    $ git push origin john
    ```

- Pull Request 생성하여 제출 완료
