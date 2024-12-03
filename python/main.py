from fastapi import FastAPI
from openai import OpenAI
from opentelemetry.instrumentation.openai import OpenAIInstrumentor
OpenAIInstrumentor().instrument()

app = FastAPI()
client = OpenAI()


@app.get("/")
async def root():
    completion = client.chat.completions.create(
    model="gpt-4o-mini",
    messages=[
        {"role": "system", "content": "You are a helpful assistant."},
        {
            "role": "user",
            "content": "Write a haiku about recursion in programming."
        }
    ])
    return {"message": completion.choices[0].message}
