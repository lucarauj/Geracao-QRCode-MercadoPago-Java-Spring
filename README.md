# Projeto Geração de QRCode MercadoPago com Java - Spring

<br>

## Criando Aplicação no MercadoPago

- ```https://www.mercadopago.com.br/developers/pt```
- Suas integrações
- Criar aplicação (Pagamentos on-line | Não | CheckoutTransparente | Marketplace) 
- Ativar Credenciais de produção
- ```https://auth.mercadopago.com/authorization?client_id=APP_ID&response_type=code&platf orm_id=mp&state=RANDOM_ID&redirect_uri=https://www.redirect-url.com```

<br>

## Utilizando o Postman

- ```https://api.mercadopago.com/oauth/token```
- body:
```
{
    "client_id": "client_id",
    "client_secret": "client_secret",
    "code": "TG-XXXXXXXX-241983636",
    "grant_type": "authorization_code",
    "redirect_uri": "uri",
    "test_token": true
}
```

<br>

## Dependências

- Spring Web
- Javax WS RS API

<br>

## Anotações

- @Autowired
- @Bean
- @Component
- @Configuration
- @Consumes(MediaType.APPLICATION_JSON)
- @HeaderParam
- @JsonProperty
- @Path
- @POST
- @PostMapping
- @Produces(MediaType.APPLICATION_JSON)
- @RestController
- @Repository
- @RequestMapping
- @Service

<br>

## Requisição para gerar Bearer Token no navegador:

```
https://auth.mercadopago.com/authorization?client_id=xxxxxxxxxxxxxxxx&response_type=code&platform_id=mp&state=10&redirect_uri=https://www.redirect-url.com
```
- Exemplo de resposta:

```
TG-656276ce0c63da00017ce7b1-xxxxxxxxx
```

<br>

## Requisição QRCode:

- Uri da Requisição:

```
https://api.mercadopago.com/v1/payments
```

<br>

- body:

```
{
    "description": "Payment for product",
    "payer": {
        "email": "lucarauj@gmail.com"
    },
    "payment_method_id": "pix",
    "transaction_amount": 58.8
}
```

<br>

- Headers:

```
Key: X-Idempotency-Key | Value: número aleatório
```

<br>

- Resposta:

```
{
    "id": 13160xxxxx,
    "date_created": "2023-11-25T14:40:30.155-04:00",
    "date_approved": null,
    "date_last_updated": "2023-11-25T14:40:30.155-04:00",
    "date_of_expiration": "2023-11-26T14:40:29.908-04:00",
    "money_release_date": null,
    "money_release_status": "released",
    "operation_type": "regular_payment",
    "issuer_id": "12501",
    "payment_method_id": "pix",
    "payment_type_id": "bank_transfer",
    "payment_method": {
        "id": "pix",
        "type": "bank_transfer",
        "issuer_id": "12xxx"
    },
    "status": "pending",
    "status_detail": "pending_waiting_transfer",
    "currency_id": "BRL",
    "description": "Payment for product",
    "live_mode": false,
    "sponsor_id": null,
    "authorization_code": null,
    "money_release_schema": null,
    "taxes_amount": 0,
    "counter_currency": null,
    "brand_id": null,
    "shipping_amount": 0,
    "build_version": "3.27.0-rc-2",
    "pos_id": null,
    "store_id": null,
    "integrator_id": null,
    "platform_id": null,
    "corporation_id": null,
    "payer": {
        "identification": {
            "number": "3265xxxx",
            "type": "DNI"
        },
        "entity_type": null,
        "phone": {
            "number": null,
            "extension": null,
            "area_code": null
        },
        "last_name": null,
        "id": "356257551",
        "type": null,
        "first_name": null,
        "email": "test_user_8050xxxx@testuser.com"
    },
    "collector_id": 18417xxxx,
    "marketplace_owner": null,
    "metadata": {},
    "additional_info": {
        "available_balance": null,
        "nsu_processadora": null,
        "authentication_code": null
    },
    "order": {},
    "external_reference": null,
    "transaction_amount": 58.8,
    "transaction_amount_refunded": 0,
    "coupon_amount": 0,
    "differential_pricing_id": null,
    "financing_group": null,
    "deduction_schema": null,
    "callback_url": null,
    "installments": 1,
    "transaction_details": {
        "payment_method_reference_id": null,
        "acquirer_reference": null,
        "net_received_amount": 0,
        "total_paid_amount": 58.8,
        "overpaid_amount": 0,
        "external_resource_url": null,
        "installment_amount": 0,
        "financial_institution": null,
        "payable_deferral_period": null,
        "bank_transfer_id": null,
        "transaction_id": null
    },
    "fee_details": [],
    "charges_details": [
        {
            "id": "13160xxxxx-001",
            "name": "mercadopago_fee",
            "type": "fee",
            "accounts": {
                "from": "collector",
                "to": "mp"
            },
            "client_id": 0,
            "date_created": "2023-11-25T14:40:30.163-04:00",
            "last_updated": "2023-11-25T14:40:30.163-04:00",
            "amounts": {
                "original": 0.58,
                "refunded": 0
            },
            "metadata": {},
            "reserve_id": null,
            "refund_charges": []
        }
    ],
    "captured": true,
    "binary_mode": false,
    "call_for_authorize_id": null,
    "statement_descriptor": null,
    "card": {},
    "notification_url": null,
    "refunds": [],
    "processing_mode": "aggregator",
    "merchant_account_id": null,
    "merchant_number": null,
    "acquirer_reconciliation": [],
    "point_of_interaction": {
        "type": "OPENPLATFORM",
        "business_info": {
            "unit": "online_payments",
            "sub_unit": "default"
        },
        "location": {
            "state_id": null,
            "source": null
        },
        "application_data": {
            "name": null,
            "version": null
        },
        "transaction_data": {
            "qr_code": "xxxxxxxxxxxxbr.gov.bcb.pixxxxb76axxxx-2ec4-41xx-954e-ebfe34f05bxxxxx40000530xxxxxxxxxx.xxxxx2BRxxxxLU35IllQhs1xxxx5ArAos62230519mpqrinterxxxxxxxxxxxxxxxxD2",
            "bank_transfer_id": null,
            "transaction_id": null,
            "e2e_id": null,
            "financial_institution": null,
            "ticket_url": "https://www.mercadopago.com.br/sandbox/payments/13160xxxxx/ticket?caller_id=3562xxxxx&hash=15a3xxxd-5xxx-405f-b294-d0baeff9xxxc",
            "bank_info": {
                "payer": {
                    "account_id": null,
                    "id": null,
                    "long_name": null,
                    "account_holder_name": null,
                    "identification": {
                        "number": null,
                        "type": null
                    },
                    "external_account_id": null
                },
                "collector": {
                    "account_id": null,
                    "long_name": null,
                    "account_holder_name": "TeKt OLst",
                    "transfer_account_id": null
                },
                "is_same_bank_account_owner": null,
                "origin_bank_id": null,
                "origin_wallet_id": null
            },
            "qr_code_base64": "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"
        }
    },
    "accounts_info": null,
    "tags": null
}
```

<br>

# 👨🏼‍🎓 Aluno

Lucas Araujo

<a href="https://www.linkedin.com/in/lucarauj"><img alt="lucarauj | LinkdeIN" width="40px" src="https://user-images.githubusercontent.com/43545812/144035037-0f415fc7-9f96-4517-a370-ccc6e78a714b.png" /></a>
