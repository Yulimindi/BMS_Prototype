<h1>🏦 [Project] Spring Boot 기반 권한별 은행 시스템 백엔드 구현</h1>

<p>
  <img src="https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white">
  <img src="https://img.shields.io/badge/SpringBoot-4.0.5-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">
  <img src="https://img.shields.io/badge/SpringSecurity-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white">
  <img src="https://img.shields.io/badge/Oracle-F80000?style=for-the-badge&logo=oracle&logoColor=white">
</p>

<p>Spring Boot 4.0.5와 Security, JWT를 활용하여 보안성이 강화된 은행 핵심 로직을 구현한 프로젝트입니다.</p>

<hr>

<h2>1. 프로젝트 개요</h2>
<ul>
  <li><strong>목적</strong>: 권한(User, Admin)에 따른 차등적 기능 제공 및 트랜잭션 기반의 안정적인 뱅킹 시스템 구축</li>
  <li><strong>기간</strong>: 2026.04.09 ~ 2026.04.10</li>
  <li><strong>기술 스택</strong>:
    <ul>
      <li><strong>Backend</strong>: Java 21, Spring Boot 4.0.5, Spring Data JPA, Spring Security</li>
      <li><strong>Security</strong>: JWT (Access Token), BCrypt Password Encoding</li>
      <li><strong>Database</strong>: Oracle</li>
    </ul>
  </li>
</ul>

<hr>

<h2>2. 구현 기능 (Feature List)</h2>

<h3>✅ 구현 완료 (Completed)</h3>
<ul>
  <li><strong>보안 및 인증</strong>
    <ul>
      <li>BCrypt를 이용한 비밀번호 암호화 저장</li>
      <li>JWT 발급 및 OncePerRequestFilter를 통한 토큰 검증</li>
      <li>Role-based Access Control (RBAC): hasRole()을 이용한 API 접근 제어</li>
    </ul>
  </li>
  <li><strong>뱅킹 비즈니스 로직</strong>
    <ul>
      <li>회원가입 시 기본 계좌 자동 생성 시스템</li>
      <li>입금 및 송금 기능 (Service 계층 분리)</li>
      <li><strong>트랜잭션 원자성 보장</strong>: 송금 시 잔액 부족 예외 처리 및 롤백</li>
    </ul>
  </li>
  <li><strong>조회</strong>
    <ul>
      <li>내 계좌 잔액 및 최근 거래 내역 5건 조회</li>
    </ul>
  </li>
</ul>

<h3>🚧 미구현 또는 향후 과제 (To-do / Backlog)</h3>
<ul>
  <li>Refresh Token 도입을 통한 보안 강화</li>
  <li>Redis를 활용한 블랙리스트 처리</li>
</ul>

<hr>

<h2>3. 핵심 기술 구현 상세</h2>

<h3>🛡️ Spring Security & JWT 설정</h3>
<p><strong>문제 해결</strong>: 세션 방식이 아닌 Stateless한 JWT 방식을 채택하여 서버 부하를 줄임.</p>
<p><strong>설정 핵심</strong>: SecurityFilterChain에서 <code>/admin/**</code> 경로는 ADMIN 권한만, <code>/member/**</code>은 USER 권한만 접근 가능하도록 엄격히 분리함.</p>

<h3>💸 트랜잭션 관리 및 예외 처리</h3>
<p><strong>원자성 보장</strong>: <code>@Transactional</code> 어노테이션을 사용하여 송금 과정 중 한쪽 계좌에서만 돈이 빠져나가는 사고를 방지함.</p>
<p><strong>Custom Exception</strong>: <code>InsufficientBalanceException</code> 등 비즈니스 예외를 정의하고 GlobalExceptionHandler를 통해 클라이언트에게 적절한 응답 코드(예: 400 Bad Request)를 반환하도록 설계함.</p>

<hr>

<h2>4. 개발 과정에서의 성장 및 학습 포인트</h2>

<h3>💡 기술적 실력 향상</h3>
<ul>
  <li><strong>인증 방식의 고도화 (Cookie 활용)</strong>: 보안상 취약할 수 있는 Authorization 헤더 방식 대신, <strong>HttpOnly 및 Secure 설정이 적용된 Cookie</strong>에 JWT를 담아 전달함으로써 XSS 공격 방어 능력을 키움.</li>
  <li><strong>Java Stream API의 적극 활용</strong>: 거래 내역 조회 및 데이터 필터링 시 <strong>Stream API</strong>를 사용하여 반복문을 제거하고, 선언적 코드 작성을 통해 가독성과 유지보수성을 향상시킴.</li>
  <li><strong>DB 표준 이해</strong>: 오라클의 FETCH NEXT 문법을 사용해 페이징 처리를 최적화하고, Native Query와 JPA의 작동 차이를 이해함.</li>
  <li><strong>계층형 아키텍처의 이해</strong>: Controller-Service-Repository 구조를 엄격히 준수. 특히 Entity와 DTO를 분리하여 데이터 모델 간의 결합도를 낮추고 보안성을 높임.</li>
  <li><strong>Spring Security 적응</strong>: 6.x 버전의 Lambda DSL 설정 방식과 SecurityFilterChain 빈 등록 방식에  익숙해짐.</li>
</ul>

<h3>❓ 어려웠던 점과 해결 과정</h3>
<ul>
  <li><strong>문제</strong>: @CreatedDate를 설정했음에도 거래 내역(Transaction) 저장 시 날짜(createdAt)가 계속 null로 들어가는 현상이 발생함.</li>
  <li><strong>원인 파악</strong>: JPA Auditing은 JPA의 영속성 컨텍스트를 거칠 때만 작동하는데, 직접 INSERT 문을 작성하는 Native Query를 사용하여 JPA의 생명주기 관리 로직을 건너뛰었기 때문임을 확인함.</li>
  <li><strong>해결</strong>: 오라클 Native Query 내부에 SYSDATE를 직접 추가하여 DB 레벨에서 시간을 기록함.</li>
</ul>

<ul>
  <li><strong>문제</strong>: 계좌 상세 페이지 조회 시 서버가 멈추고 StackOverflowError 로그가 수천 줄 찍히는 오류 발생.</li>
  <li><strong>원인 파악</strong>: MemberEntity와 AccountEntity가 서로를 참조하는 양방향 관계에서, Lombok의 @Data나 @ToString이 서로의 toString()을 무한하게 호출하는 순환 참조(Circular Reference) 현상임을 로그를 통해 발견.</li>
  <li><strong>해결</strong>: @Data를 제거하고 @Getter와 @Setter를 사용하여 출력 시 순환 참조 고리를 끊어내어 안정적인 조회가 가능하도록 조치함.</li>
</ul>

<ul>
  <li><strong>문제</strong>: @ManyToOne 필드에 일반 컬럼용 어노테이션인 @Column을 사용하거나, @JoinColumn만 쓰고 관계 어노테이션(@ManyToOne)을 누락하여 BeanCreationException 발생.</li>
  <li><strong>원인 파악</strong>: JPA에서 객체 간의 관계는 단순 데이터(Column)가 아니라 연결(Join)로 처리해야 한다는 원칙을 어김.</li>
  <li><strong>해결</strong>: @Column을 제거하고 @JoinColumn으로 교체, 엔티티 타입 필드 위에는 @ManyToOne과 같은 관계 명시 어노테이션을 추가함.</li>
</ul>

<hr>

<h2>5. 테스트 시나리오 결과</h2>
<table border="1">
  <tr>
    <th>시나리오</th>
    <th>기대 결과</th>
    <th>상태</th>
  </tr>
  <tr>
    <td>로그인 없이 송금 API 호출</td>
    <td>401 Unauthorized 반환</td>
    <td>✅ 통과</td>
  </tr>
  <tr>
    <td>잔액 부족 상태로 송금 시도</td>
    <td>Exception 발생 및 DB 롤백 확인</td>
    <td>✅ 통과</td>
  </tr>
  <tr>
    <td>일반 계정으로 관리자 API 접근</td>
    <td>403 Forbidden 반환</td>
    <td>✅ 통과</td>
  </tr>
</table>
