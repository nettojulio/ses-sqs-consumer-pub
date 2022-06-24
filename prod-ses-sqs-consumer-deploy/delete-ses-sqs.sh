kubectl delete -f /home/ubuntu/grupo08/prod-ses-sqs-consumer-deploy/configmap-ses-sqs.yaml --namespace=grupo-08-prod
kubectl delete -f /home/ubuntu/grupo08/prod-ses-sqs-consumer-deploy/clusterip-ses-sqs.yaml --namespace=grupo-08-prod
kubectl delete -f /home/ubuntu/grupo08/prod-ses-sqs-consumer-deploy/deployment-ses-sqs.yaml --namespace=grupo-08-prod
kubectl delete -f /home/ubuntu/grupo08/prod-ses-sqs-consumer-deploy/hpa-ses-sqs.yaml --namespace=grupo-08-prod