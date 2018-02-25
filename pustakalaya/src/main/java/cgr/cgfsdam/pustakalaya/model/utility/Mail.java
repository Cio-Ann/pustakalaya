package cgr.cgfsdam.pustakalaya.model.utility;

/**
 * Entidad que representa un email sencillo.
 *
 * @author CGR-Casa
 */
public class Mail {
	/**
	 * Remitente.
	 */
	private String from;
	/**
	 * Destinatario.
	 */
	private String to;
	/**
	 * Asunto.
	 */
	private String subject;
	/**
	 * Cuerpo del mensaje.
	 */
	private String body;

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	/**
	 * Constructor por defecto.
	 */
	public Mail() {
	}

	/**
	 * Constructor por parámetros.
	 * 
	 * @param from String remitente
	 * @param to String destinatario
	 * @param subject String asunto
	 * @param body String cuerpo
	 */
	public Mail(String from, String to, String subject, String body) {
		super();
		this.from = from;
		this.to = to;
		this.subject = subject;
		this.body = body;
	}

	@Override
	public String toString() {
		return "Mail [from=" + from + ", to=" + to + ", subject=" + subject + ", body=" + body + "]";
	}

}
