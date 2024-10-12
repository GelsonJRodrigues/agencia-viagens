```Mermaid
 CLIENTES {
        int id_cliente PK
        string nome
        string endereco
        string telefone
        string email
    }
    
    RESERVAS {
        int id_reserva PK
        date data_reserva
        date data_viagem
        int id_cliente FK
        int id_pacote FK
    }

    PACOTES {
        int id_pacote PK
        string descricao
        float preco
        int duracao_dias
    }

    PAGAMENTO {
        int id_pagamento PK
        date data_pagamento
        float valor_pago
        string metodo_pagamento
        int id_reserva FK
    }

    CLIENTES ||--o{ RESERVAS : faz
    PACOTES ||--o{ RESERVAS : inclui
    RESERVAS ||--o{ PAGAMENTO : gera
```
