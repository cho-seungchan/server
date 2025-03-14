const readService = (() => {
//     추가
    const writeQuestion = async (question) => {
        await fetch("/proposal/writeQuestion", {
            method: "post",
            body: JSON.stringify(question),
            headers: {
                "Content-Type": "application/json;charset=utf-8"
            }
        });
    }
    return{
        writeQuestion:writeQuestion
    };
}) ();
