FROM python:3.10-slim

COPY requirements.txt ./
RUN pip install --no-cache-dir -r requirements.txt

COPY . /fire

WORKDIR /fire


CMD ["python", "fire.py" ]
