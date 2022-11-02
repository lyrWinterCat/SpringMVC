package hello.upload.domain;

import lombok.Data;

@Data
public class UploadFile {
    // 여러 사용자가 같은 이름의 파일을 업로드 했을 시 구분하기 위한 fileName

    private String uploadFileName; //고객 업로드 파일명
    private String storeFileName; // 서버 내부 관리하는 파일명

    public UploadFile(String uploadFileName, String storeFileName) {
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
    }
}
