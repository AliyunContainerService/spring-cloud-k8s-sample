{/* vim: set filetype=mustache: */}}
{{/*
Expand the name of the chart.
*/}}
{{- define "hystrix.name" -}}
{{- default .Chart.Name .Values.nameOverride | trunc 63 | trimSuffix "-" -}}
{{- end -}}

{{/*
Create a default fully qualified app name.
We truncate at 63 chars because some Kubernetes name fields are limited to this (by the DNS naming spec).
*/}}
{{- define "hystrix.fullname" -}}
{{- $name := default .Chart.Name .Values.nameOverride -}}
{{- printf "%s-%s" .Release.Name $name | trunc 63 | trimSuffix "-" -}}
{{- end -}}

{{- define "eurekaUrl" -}}
{{- end -}}

{{- define "eurekaConfig" }}
{{- $eurekaHostname :=  (printf "%s-spring-eureka-server" .Release.Name) -}}
{{- $eurekaPort := 8761 -}}

{{- if .Values.global.eureka.service.name -}}
{{- $eurekaHostname := .Values.global.eureka.hostname -}}
{{- end -}}

{{- if .Values.global.eureka.service.name -}}
{{- $eurekaPort := .Values.global.eureka.port -}}
{{- end }}
    eureka:
      instance:
        preferIpAddress: true
      client:
        enabled: {{ .Values.eureka.client.enabled }}
        registerWithEureka: true
        fetchRegistry: true
        serviceUrl:
          defaultZone: {{ .Values.eureka.client.serviceUrl.defaultZone }}
{{ end -}}
