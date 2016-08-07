#!/bin/bash
curl -i -H "Content-Type: application/json" -X POST --data-binary "@../json/order3.json" http://localhost:9082/api/v1/salestaxes/purchase 