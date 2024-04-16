package com.board.notice.common;

/**
 * exception 정의 class
 */
public class Exceptions {

    /**
     * GlobalExceptionHandler 에서 exception trace 를 로깅 하고 싶지 않을 때 상속 하여 사용
     */
    public static class NoTrace extends RuntimeException {
        public NoTrace(String msg) {
            super(msg);
        }

        public NoTrace(String msg, Exception e) {
            super(msg, e);
        }
    }

    /**
     * 데이터정보없음
     */
    public static class NotFoundData extends NoTrace {
        public NotFoundData() {
            super(NotFoundData.class.getSimpleName());
        }

        public NotFoundData(String msg) {
            super(msg);
        }
    }

    /**
     * 권한정보없음
     */
    public static class NotFoundAuth extends NoTrace {
        public NotFoundAuth() {
            super(NotFoundAuth.class.getSimpleName());
        }
    }

    /**
     * 응답 status 코드확인 실패
     */
    public static class APIResponseStatusNotValid extends NoTrace {
        public APIResponseStatusNotValid(String msg) {
            super(msg);
        }
    }

    /**
     * 요청 정보 검증 실패
     */
    public static class APIRequestInfoNotValid extends NoTrace {
        public APIRequestInfoNotValid(String msg) {
            super(msg);
        }
    }

    /**
     * opensearch request 객체검증 실패
     */
    public static class DashbaordRequestParseException extends Exception {
        public DashbaordRequestParseException(String msg) {
            super(msg);
        }
    }

    /**
     * opensearch 요청실패
     */
    public static class DashbaordRequestException extends Exception {
        public DashbaordRequestException(String msg) {
            super(msg);
        }
    }

    /**
     * event 상태 업데이트 실패
     */
    public static class EventStatusUpdateException extends Exception {
        public EventStatusUpdateException(String msg) {
            super(msg);
        }
    }

    /**
     * event comments 조회 실패
     */
    public static class EventCommentsRequestException extends Exception {
        public EventCommentsRequestException(String msg) {
            super(msg);
        }
    }

    /**
     * event detail조회 실패
     */
    public static class EventDetailException extends Exception {
        public EventDetailException(String msg) {
            super(msg);
        }
    }

    /**
     * event timeseries 조회 실패
     */
    public static class EventTimeSeriesException extends Exception{
        public EventTimeSeriesException(String msg){
            super(msg);
        }
    }

    /**
     * 데이터정보없음
     */
    public static class NoContent extends NoTrace {

        public NoContent(String msg) {
            super(msg);
        }
    }

    /**
     * openfeign 응답 실패
     */
    public static class OpenfeignException extends Exception{

        public OpenfeignException(String msg, Exception e) {
            super(msg, e);
        }
    }

    /**
     * 요청 정보 검증 실패
     */
    public static class NoSuchElementException extends NoTrace {
        public NoSuchElementException(String msg) {
            super(msg);
        }
    }

    /**
     * 요청 정보 검증 실패
     */
    public static class IllegalArgumentException extends NoTrace {
        public IllegalArgumentException(String msg) {
            super(msg);
        }
    }

	/**
	 * 인증 실패
	 */
	public static class BadCredentialsException extends NoTrace {
		public BadCredentialsException(String msg) {
			super(msg);
		}
	}

    /**
     * 토큰 검증 실패
     */
    public static class JwtTokenException extends NoTrace {
        public JwtTokenException(String msg) {  super(msg); }
    }
}
