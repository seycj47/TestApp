=================== TLS 
1. OEM 인증서 발급

2. CP / CPOS Cert 발급
  2.1. issue
  2.2. get issued cert by cn
  op) exception

3. Chain 발급 (CERT_PATH 환경변수 사용)

4. TLS 테스트 (EV - EVSE)
- mbed TLS 
- 한전 cyper suit : aria
- $ java -cp ./wa-ssl_proxy.jar -Djava.library.path=./wassl/lib tls_proxy.TlsProxy 0.0.0.0 18085 120.216.20.13 443 CLI
- param : bindIP, port, TargetIP, port
 3.1. EV TLS CLI 구동
 3.2. EVSE TLS CLI 구동
 3.3. 데이터 주고받기 HOW?


=================== Contract
1. MO - 가입/계약

2 insatallMsg 요청 

3. 충전 시나리오 / OCSP 
- /verify(emaid verify), /tariff, /sales

4. MO - 갱신

5. updateMsg 요청  
  - 충전 시나리오 ?
  - op) mo contract 요청?
