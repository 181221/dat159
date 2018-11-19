[
    {
        "id": "19dcfeb2.6b2e71",
        "type": "tab",
        "label": "Heating Rest",
        "disabled": false,
        "info": ""
    },
    {
        "id": "3a8ece0c.d5609a",
        "type": "function",
        "z": "19dcfeb2.6b2e71",
        "name": "Random temperature",
        "func": "let temp = Math.floor((Math.random() * 10) + 20);\nmsg.payload = { \"temperature\": temp };\nmsg.headers = {'content-type':'application/json'};\nreturn msg;",
        "outputs": 1,
        "noerr": 0,
        "x": 441.14288330078125,
        "y": 265.1904602050781,
        "wires": [
            [
                "6945a96d.4dcb68"
            ]
        ]
    },
    {
        "id": "328f3fdc.4b2fd",
        "type": "inject",
        "z": "19dcfeb2.6b2e71",
        "name": "",
        "topic": "Temperature",
        "payload": "",
        "payloadType": "str",
        "repeat": "5",
        "crontab": "",
        "once": false,
        "onceDelay": 0.1,
        "x": 144.857177734375,
        "y": 266,
        "wires": [
            [
                "3a8ece0c.d5609a",
                "97c9bc5b.30da68"
            ]
        ]
    },
    {
        "id": "8329bde8.8c6538",
        "type": "function",
        "z": "19dcfeb2.6b2e71",
        "name": "Control Heating",
        "func": "\nif(typeof global.get(\"heating\") === \"undefined\"){\n    if(msg.payload < 23){\n        global.set(\"heating\", \"on\")\n        msg.payload = \"on\";\n    }\n    else {\n        global.set(\"heating\", \"off\")\n        msg.payload = \"off\";\n    }\n}\nlet heating = global.get(\"heating\")\nif(msg.payload < 23 && heating !==\"on\"){\n    msg.payload = \"on\";\n    global.set(\"heating\", \"on\")\n}else if(msg.payload > 23 && heating !== \"off\") {\n    msg.payload = \"off\";\n    global.set(\"heating\", \"off\")\n}\nreturn msg;",
        "outputs": 1,
        "noerr": 0,
        "x": 557.4999847412109,
        "y": 458.66661071777344,
        "wires": [
            [
                "5b877261.0742dc"
            ]
        ]
    },
    {
        "id": "5b877261.0742dc",
        "type": "switch",
        "z": "19dcfeb2.6b2e71",
        "name": "heating",
        "property": "payload",
        "propertyType": "msg",
        "rules": [
            {
                "t": "eq",
                "v": "on",
                "vt": "str"
            },
            {
                "t": "eq",
                "v": "off",
                "vt": "str"
            }
        ],
        "checkall": "true",
        "repair": false,
        "outputs": 2,
        "x": 782.5,
        "y": 458.6666259765625,
        "wires": [
            [
                "834a585.504e5a8"
            ],
            [
                "834a585.504e5a8"
            ]
        ]
    },
    {
        "id": "834a585.504e5a8",
        "type": "ui_text",
        "z": "19dcfeb2.6b2e71",
        "group": "7234bb5c.8e3ffc",
        "order": 3,
        "width": 0,
        "height": 0,
        "name": "",
        "label": "Heating",
        "format": "{{msg.payload}}",
        "layout": "row-spread",
        "x": 964.5,
        "y": 563.6666259765625,
        "wires": []
    },
    {
        "id": "4fe89973.885998",
        "type": "ui_gauge",
        "z": "19dcfeb2.6b2e71",
        "name": "",
        "group": "7234bb5c.8e3ffc",
        "order": 2,
        "width": 0,
        "height": 0,
        "gtype": "gage",
        "title": "Temperature",
        "label": "C",
        "format": "{{value}}",
        "min": 0,
        "max": "60",
        "colors": [
            "#00b500",
            "#e6e600",
            "#ca3838"
        ],
        "seg1": "",
        "seg2": "",
        "x": 550.5,
        "y": 366.99998474121094,
        "wires": [],
        "inputLabels": [
            "msg.payload.with.content.temperature"
        ]
    },
    {
        "id": "6945a96d.4dcb68",
        "type": "http request",
        "z": "19dcfeb2.6b2e71",
        "name": "Put temperature",
        "method": "PUT",
        "ret": "obj",
        "url": "https://dweet-spark.herokuapp.com/get/dweet/for/pederyo_dweet",
        "tls": "",
        "x": 693.5,
        "y": 265.3333435058594,
        "wires": [
            []
        ]
    },
    {
        "id": "97c9bc5b.30da68",
        "type": "http request",
        "z": "19dcfeb2.6b2e71",
        "name": "Get Temperature",
        "method": "GET",
        "ret": "obj",
        "url": "https://dweet-spark.herokuapp.com/get/dweet/for/pederyo_dweet",
        "tls": "",
        "x": 95,
        "y": 459.33331298828125,
        "wires": [
            [
                "e44ea03c.15dbb"
            ]
        ]
    },
    {
        "id": "e44ea03c.15dbb",
        "type": "function",
        "z": "19dcfeb2.6b2e71",
        "name": "Parse dweet",
        "func": "msg.payload = msg.payload.with.content.temperature\nreturn msg;",
        "outputs": 1,
        "noerr": 0,
        "x": 311.5,
        "y": 459,
        "wires": [
            [
                "8329bde8.8c6538",
                "4fe89973.885998"
            ]
        ]
    },
    {
        "id": "7234bb5c.8e3ffc",
        "type": "ui_group",
        "name": "Group 1",
        "tab": "92c14c74.563a9",
        "order": 1,
        "disp": true,
        "width": 6
    },
    {
        "id": "92c14c74.563a9",
        "type": "ui_tab",
        "z": "",
        "name": "heating",
        "icon": "dashboard",
        "order": 2
    }
]