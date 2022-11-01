package hello.typeconverter.type;

import lombok.EqualsAndHashCode;
import lombok.Getter;

//"127.0.0.1:8080"이 들어올 경우에 대한 convert를 만들기 위한 작업
@Getter
@EqualsAndHashCode
public class IpPort {

    private String ip;
    private int port;

    public IpPort(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }
}
