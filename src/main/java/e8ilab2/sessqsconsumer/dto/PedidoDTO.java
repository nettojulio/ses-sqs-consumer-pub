package e8ilab2.sessqsconsumer.dto;

import java.time.LocalDateTime;

public class PedidoDTO {
    private Integer id;

    private Integer usuarioId;

    private String usuarioName;

    private String usuarioEmail;

    private Double valorTotal;

    private String descricao;

    private String dataPedido;

    private String status;

    public PedidoDTO(Integer usuarioId, String usuarioName, String usuarioEmail, Double valorTotal, String descricao, LocalDateTime dataPedido, String status) {
        this.usuarioId = usuarioId;
        this.usuarioName = usuarioName;
        this.usuarioEmail = usuarioEmail;
        this.valorTotal = valorTotal;
        this.descricao = descricao;
        this.dataPedido = dataPedido.toString();
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getUsuarioName() {
        return usuarioName;
    }

    public void setUsuarioName(String usuarioName) {
        this.usuarioName = usuarioName;
    }

    public String getUsuarioEmail() {
        return usuarioEmail;
    }

    public void setUsuarioEmail(String usuarioEmail) {
        this.usuarioEmail = usuarioEmail;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDateTime dataPedido) {
        this.dataPedido = dataPedido.toString();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PedidoDTO{" +
                "id=" + id +
                ", usuarioId=" + usuarioId +
                ", usuarioName='" + usuarioName + '\'' +
                ", usuarioEmail='" + usuarioEmail + '\'' +
                ", valorTotal=" + valorTotal +
                ", descricao='" + descricao + '\'' +
                ", dataPedido=" + dataPedido +
                ", status='" + status + '\'' +
                '}';
    }
}
