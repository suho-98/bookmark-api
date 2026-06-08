FROM eclipse-temurin:21-jdk-jammy

#ADD와 COPY는 모두 외부에서 이미지 내부로 파일을 복사 하는 기능이다
#ADD tar,tar.gz와 같은 리눅스파일을 자동으로 압출을 해제한다
#COPY는 복사만 한다
ADD target/*.jar app.jar

#파일의 수정 시간을 현재시간을 설정
RUN sh -c 'touch /app.jar'

#애플리케이션 컨테이너로 실행할 때 내부 포트
EXPOSE 8080

#애플리케이션이 실행할 명령어 설정
ENTRYPOINT [ "sh","-c","java -jar /app.jar" ]
